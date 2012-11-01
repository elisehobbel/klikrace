package com.elise.klikrace;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

public class RaceTrackView extends View {
	
	private Rect backgroundRect;
	private RaceTrackShape raceTrackShape;
	
	private long raceStartTime;
	private Runner player;
	private long sumStartTime;

	private float percentagePlayer;
	
	private Race currentRace;
	private RaceScore playerRaceScore;
	
	private boolean started = false;
	
	
	
	public RaceTrackView(Context context) {
		
		super(context);
		
		initGraphicalObjects();
		
		currentRace = Race.createDummyRace();  //TODO get race from service
		
		player = new Runner( "player");
		
		playerRaceScore = new RaceScore(currentRace, player);
			
		percentagePlayer = 0;
		
	}



	private void initGraphicalObjects() {
		//in Android resources are limited thats why I do it  here
		backgroundRect = new Rect(0,0,0,0);
		raceTrackShape = new RaceTrackShape();
		raceTrackShape.setBaanBreedte(20);
		raceTrackShape.setBinnenStraal(80);
	}


	
	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);

		drawBackGround(canvas);
		drawTrack(canvas);
			
		raceTrackShape.setWidth(canvas.getWidth());
		raceTrackShape.setHeight(getScreenHeight());
		
		drawPlayer(canvas);
		drawOpponent1(canvas);
		drawOpponent2(canvas);
		
		invalidate();
		
	}

	private void drawTrack(Canvas canvas) {
		
		RectF rect = new RectF(0, 0, raceTrackShape.getBreedte(), raceTrackShape.getHoogte());
		
		Paint grey = new Paint();
		grey.setColor(Color.GRAY);
		grey.setStyle(Style.FILL);
		
		Paint white = new Paint();
		white.setColor(Color.WHITE);
		white.setStyle(Style.STROKE);
		
		
		float straal = raceTrackShape.getStraal(RaceTrackLane.OMTREK);
		
		canvas.drawRoundRect(rect, straal , straal, grey);
		canvas.drawRoundRect(rect, straal, straal, white);
		
		int baanBreedte = raceTrackShape.getLaneWidth();
		rect.set(baanBreedte, baanBreedte, raceTrackShape.getBreedte() - baanBreedte, raceTrackShape.getHoogte()-baanBreedte);
		
		canvas.drawRoundRect(rect, straal - baanBreedte, straal - baanBreedte, white);
		
		rect.set(baanBreedte*2, baanBreedte*2, raceTrackShape.getBreedte() - 2*baanBreedte, raceTrackShape.getHoogte()-2*baanBreedte);
		canvas.drawRoundRect(rect, straal - 2* baanBreedte, straal-2*baanBreedte, white);
		rect.set(baanBreedte*3, baanBreedte*3, raceTrackShape.getBreedte() - 3*baanBreedte, raceTrackShape.getHoogte()-3*baanBreedte);
		
		
		canvas.drawRoundRect(rect, straal-3*baanBreedte, straal-3*baanBreedte, getPaint(Color.YELLOW));
		canvas.drawRoundRect(rect, straal-3*baanBreedte, straal-3*baanBreedte, white);
		
	}

	
	
	private void drawOpponent1(Canvas canvas) {
		
		int color = Color.RED;	
		drawOpponent(canvas, currentRace.getRaceScoreFastestOpponent(), color, RaceTrackLane.BUITENBAAN);	
		
	}
	
	private void drawOpponent2(Canvas canvas) {
		
		int color = Color.GREEN;
		drawOpponent(canvas, currentRace.getRaceScoreSecondFastestOpponent(), color, RaceTrackLane.BINNENBAAN);
	}
	
	

	private void drawOpponent(Canvas canvas, RaceScore raceScore, int color, RaceTrackLane baan) {
		
		long duration = System.currentTimeMillis() - raceStartTime;		
		
		float percentageOpponent = raceScore.getTrackPercentage((int) duration);
		
		Log.d("drawOpponent "+ raceScore.getRunner(), "time: " + duration + " percentage: " + percentageOpponent);
		
		float x1 = raceTrackShape.getX(percentageOpponent, baan);
		float y1 = raceTrackShape.getY(percentageOpponent, baan);
		
		canvas.drawCircle(x1, y1, 7, getPaint(color));
	}

	private void drawPlayer(Canvas canvas) {
				
		float x = raceTrackShape.getX(percentagePlayer, RaceTrackLane.MIDDENBAAN);
		float y = raceTrackShape.getY(percentagePlayer, RaceTrackLane.MIDDENBAAN);
		
		canvas.drawCircle(x, y, 7, getPaint(Color.BLUE));
	}

	private void drawBackGround(Canvas canvas) {
		backgroundRect.right = canvas.getWidth();
        backgroundRect.bottom = getScreenHeight();
        
		canvas.drawRect(backgroundRect, getPaint(Color.YELLOW));
	}
	
	private Paint getPaint(int color) {
		
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		return paint;
	}

	
	public int getScreenHeight() {
		
		Rect outRect = new Rect();
		getDrawingRect(outRect);		
		return outRect.height();
	}

	public void checkSum() {//TODO better

		if(sumStartTime == 0){
			sumStartTime = raceStartTime;
		}
		
		Integer scoreTime = (int) (System.currentTimeMillis() - sumStartTime);		
		playerRaceScore.addScore(currentRace.getCurrentSum(), scoreTime);//TODO this needs to be saved
		increasePercentagePlayer();
		sumStartTime = System.currentTimeMillis();
		
		if(currentRace.hasNextSum()){
			currentRace.nextSum();
		}else{
			started = false;
		
		}	
	}

	

	private void increasePercentagePlayer() {
		percentagePlayer += 100.0/currentRace.getSommen().size();
	}	

	public  String getSumStr() {	
		if(started == false){//TODO ugly next screen needs to b shown
			return "done";
		}
		return currentRace.getCurrentSum().toString();
	}

	public void startRace() {
		started = true;
		raceStartTime = System.currentTimeMillis();		
	}

}
