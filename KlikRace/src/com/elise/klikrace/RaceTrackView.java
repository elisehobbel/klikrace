package com.elise.klikrace;


import java.util.ArrayList;
import java.util.HashMap;

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
	private Renbaan renbaan;
	private Runner opponent1;
	private Runner opponent2;
	private long raceStartTime;
	private Runner player;
	private long sumStartTime;

	private Score playerScore;
	private float percentagePlayer;
	
	private Som currentSom;
	private Race currentRace;
	
	
	public RaceTrackView(Context context) {
		
		super(context);
		backgroundRect = new Rect(0,0,0,0);
		renbaan = new Renbaan();
		renbaan.setBaanBreedte(20);
		renbaan.setBinnenStraal(80);
		raceStartTime = System.currentTimeMillis();
		
		
		
		ArrayList<Som> sommen = new ArrayList<Som>();
		sommen.add(new Som("1+8"));
		sommen.add(new Som("4+3"));
		sommen.add(new Som("2+3"));
		
		
		
		currentRace = new Race(sommen);
		
		opponent1 = new Runner("opponent1");		
		opponent2 = new Runner("opponent2");
		
			
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
		
	
		drawPlayer(canvas);
		
		drawOpponent1(canvas);
		drawOpponent2(canvas);
		
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
		
		
		float straal = renbaan.getStraal(RenBaanBaan.OMTREK);
		System.out.println("straal:" + straal);
		canvas.drawRoundRect(rect, straal , straal, grey);
		canvas.drawRoundRect(rect, straal, straal, white);
		
		int baanBreedte = renbaan.getBaanBreedte();
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
		
		drawOpponent(canvas, opponent1, color, RenBaanBaan.BUITENBAAN);
		
	}
	
	private void drawOpponent2(Canvas canvas) {

		int color = Color.GREEN;
		
		drawOpponent(canvas, opponent2, color, RenBaanBaan.BINNENBAAN);
		
	}

	private void drawOpponent(Canvas canvas, Runner runner, int color, RenBaanBaan baan) {
		long duration = System.currentTimeMillis() - raceStartTime;		
		
		float percentageOpponent = runner.getTrackPercentage((int) duration, currentRace);
		
		Log.d("drawOpponent "+ runner.getName(), "time: " + duration + " percentage: " + percentageOpponent);
		
		float x1 = renbaan.getX(percentageOpponent, baan);
		float y1 = renbaan.getY(percentageOpponent, baan);
		
		canvas.drawCircle(x1, y1, 7, getPaint(color));
	}

	private void drawPlayer(Canvas canvas) {
		
		
		float x = renbaan.getX(percentagePlayer, RenBaanBaan.MIDDENBAAN);
		float y = renbaan.getY(percentagePlayer, RenBaanBaan.MIDDENBAAN);
		
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

	public void increasePercentageAndCaptureScore() {//TODO better name

		if(sumStartTime == 0){
			sumStartTime = raceStartTime;
		}
		
		Integer scoreTime = (int) (System.currentTimeMillis() - sumStartTime);

		Log.d("elise","currentScore: " + scoreTime);
		
	
		Score score = new Score(currentRace,currentSom,scoreTime);
		
		
		player.addScore(score);
		
		sumStartTime = System.currentTimeMillis();
	
		
	}

}
