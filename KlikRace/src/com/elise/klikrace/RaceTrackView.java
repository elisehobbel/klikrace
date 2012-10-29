package com.elise.klikrace;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
	private RaceTrackShape renbaan;
	
	private RaceScore raceScoreOpponent1;
	private RaceScore raceScoreOpponent2;
	
	private long raceStartTime;
	private Runner player;
	private long sumStartTime;

	private float percentagePlayer;
	
	private RaceTrack currentRace;
	private RaceScore playerRaceScore;
	private Sum currentSom;
	private boolean started = false;
	
	
	public RaceTrackView(Context context) {
		
		super(context);
		backgroundRect = new Rect(0,0,0,0);
		renbaan = new RaceTrackShape();
		renbaan.setBaanBreedte(20);
		renbaan.setBinnenStraal(80);
		
		
		
		ArrayList<Sum> sommen = new ArrayList<Sum>();
		sommen.add(new Sum("1+8"));
		sommen.add(new Sum("4+3"));
		sommen.add(new Sum("2+3"));
		
		
		currentRace = new RaceTrack(sommen);
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
		
			
		player = new Runner( "player");
		percentagePlayer = 0;
		
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
	 
		drawBackGround(canvas);
		
		drawTrack(canvas);
			
		renbaan.setWidth(canvas.getWidth());
		renbaan.setHeight(getScreenHeight());
		
		if(started){
		drawPlayer(canvas);
		
		drawOpponent1(canvas);
		drawOpponent2(canvas);
		
		
		}
		
		invalidate();
		
	}

	private void drawTrack(Canvas canvas) {
		
		RectF rect = new RectF(0, 0, renbaan.getBreedte(), renbaan.getHoogte());
		
		Paint grey = new Paint();
		grey.setColor(Color.GRAY);
		grey.setStyle(Style.FILL);
		
		Paint white = new Paint();
		white.setColor(Color.WHITE);
		white.setStyle(Style.STROKE);
		
		
		float straal = renbaan.getStraal(RaceTrackLaneShape.OMTREK);
		System.out.println("straal:" + straal);
		canvas.drawRoundRect(rect, straal , straal, grey);
		canvas.drawRoundRect(rect, straal, straal, white);
		
		int baanBreedte = renbaan.getLaneWidth();
		rect.set(baanBreedte, baanBreedte, renbaan.getBreedte() - baanBreedte, renbaan.getHoogte()-baanBreedte);
		
		canvas.drawRoundRect(rect, straal - baanBreedte, straal - baanBreedte, white);
		
		rect.set(baanBreedte*2, baanBreedte*2, renbaan.getBreedte() - 2*baanBreedte, renbaan.getHoogte()-2*baanBreedte);
		canvas.drawRoundRect(rect, straal - 2* baanBreedte, straal-2*baanBreedte, white);
		rect.set(baanBreedte*3, baanBreedte*3, renbaan.getBreedte() - 3*baanBreedte, renbaan.getHoogte()-3*baanBreedte);
		
		
		canvas.drawRoundRect(rect, straal-3*baanBreedte, straal-3*baanBreedte, getPaint(Color.YELLOW));
		canvas.drawRoundRect(rect, straal-3*baanBreedte, straal-3*baanBreedte, white);
		
	}

	private void drawOpponent1(Canvas canvas) {
		int color = Color.RED;	
		drawOpponent(canvas, raceScoreOpponent1, color, RaceTrackLaneShape.BUITENBAAN);	
	}
	
	private void drawOpponent2(Canvas canvas) {
		int color = Color.GREEN;
		
		drawOpponent(canvas, raceScoreOpponent2, color, RaceTrackLaneShape.BINNENBAAN);
	}
	
	

	private void drawOpponent(Canvas canvas, RaceScore raceScore, int color, RaceTrackLaneShape baan) {
		
		long duration = System.currentTimeMillis() - raceStartTime;		
		
		float percentageOpponent = raceScore.getTrackPercentage((int) duration);
		
		Log.d("drawOpponent "+ raceScore.getRunner(), "time: " + duration + " percentage: " + percentageOpponent);
		
		float x1 = renbaan.getX(percentageOpponent, baan);
		float y1 = renbaan.getY(percentageOpponent, baan);
		
		canvas.drawCircle(x1, y1, 7, getPaint(color));
	}

	private void drawPlayer(Canvas canvas) {
		
		
		float x = renbaan.getX(percentagePlayer, RaceTrackLaneShape.MIDDENBAAN);
		float y = renbaan.getY(percentagePlayer, RaceTrackLaneShape.MIDDENBAAN);
		
		canvas.drawCircle(x, y, 7, getPaint(Color.BLUE));
		
		//drawOpponent(canvas, playerRaceScore, Color.BLUE, RenBaanBaan.MIDDENBAAN);
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

	public void increasePercentageAndCaptureScore() {//TODO better name

		if(sumStartTime == 0){
			sumStartTime = raceStartTime;
		}
		
		Integer scoreTime = (int) (System.currentTimeMillis() - sumStartTime);

		
		int somIndex = 0;
		
		if(currentSom != null){//TODO ugly
			somIndex = currentRace.getSommen().indexOf(currentSom) + 1;
		}
		
		
		if(somIndex < currentRace.getSommen().size()){
		
		currentSom = currentRace.getSommen().get(somIndex);
		
		playerRaceScore.addScore(currentSom, scoreTime);
	    
		sumStartTime = System.currentTimeMillis();
		
		}else{
			//TODO finish
		}
		
	}

	public  String getSomStr() {
		
		return currentSom.getSomString();
	}

	public void startRace() {
		started = true;
		raceStartTime = System.currentTimeMillis();
		
	}

}
