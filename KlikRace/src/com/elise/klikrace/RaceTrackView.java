package com.elise.klikrace;

import java.util.ArrayList;

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
	
	private RaceScore raceScoreOpponent1;
	private RaceScore raceScoreOpponent2;
	
	private long raceStartTime;
	private Runner player;
	private long sumStartTime;

	private float percentagePlayer;
	
	private RaceTrack currentRace;
	private RaceScore playerRaceScore;
	
	private boolean started = false;
	
	
	public RaceTrackView(Context context) {
		
		super(context);
		backgroundRect = new Rect(0,0,0,0);
		raceTrackShape = new RaceTrackShape();
		raceTrackShape.setBaanBreedte(20);
		raceTrackShape.setBinnenStraal(80);

		//dummy data
		ArrayList<Sum> sommen = new ArrayList<Sum>();
		sommen.add(new Sum("1+8"));
		sommen.add(new Sum("4+3"));
		sommen.add(new Sum("2+3"));
		
		currentRace = new RaceTrack(sommen);
		player = new Runner( "player");
		
		playerRaceScore = new RaceScore(currentRace, player);
		
		Runner opponent1 = new Runner("opponent1");		
		Runner opponent2 = new Runner("opponent2");
		
		raceScoreOpponent1 = new RaceScore(currentRace, opponent1);
		raceScoreOpponent1.addScore(currentRace.getSommen().get(0), 3333);
		raceScoreOpponent1.addScore(currentRace.getSommen().get(1), 3333);
		raceScoreOpponent1.addScore(currentRace.getSommen().get(2), 3333);
		
		raceScoreOpponent2 = new RaceScore(currentRace, opponent2);
		raceScoreOpponent2.addScore(currentRace.getSommen().get(0), 4444);
		raceScoreOpponent2.addScore(currentRace.getSommen().get(1), 3333);
		raceScoreOpponent2.addScore(currentRace.getSommen().get(2), 1111);
			
		percentagePlayer = 0;
		
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
		drawOpponent(canvas, raceScoreOpponent1, color, RaceTrackLane.BUITENBAAN);	
	}
	
	private void drawOpponent2(Canvas canvas) {
		int color = Color.GREEN;
		drawOpponent(canvas, raceScoreOpponent2, color, RaceTrackLane.BINNENBAAN);
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
			// TODO finished
		}	
	}

	private void increasePercentagePlayer() {
		percentagePlayer += 100.0/currentRace.getSommen().size();
	}

	public  String getSumStr() {	
		if(started == false){//TODO ugly next screen needs to b shown
			return "done";
		}
		return currentRace.getCurrentSum().getSomString();
	}

	public void startRace() {
		started = true;
		raceStartTime = System.currentTimeMillis();		
	}

}
