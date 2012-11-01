package com.elise.klikrace;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.klikrace.R;

public class KlikRaceActivity extends Activity  {

    private Button raceButton;
	private RaceTrackView raceTrackView;
	private OnClickListener myKlikListener;
	private OnClickListener myStartListener;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klik_race);
        
        initOnClickListeners();
        
    	raceButton = (Button) findViewById(R.id.button1);
		raceButton.setText("Start");
		
		raceButton.setOnClickListener(myStartListener);	
		
		raceTrackView = new RaceTrackView(this);
		
		FrameLayout layout = (FrameLayout) findViewById(R.id.myFrame);

		layout.addView(raceTrackView);

		raceButton.bringToFront();
		
		startService(new Intent(KlikRaceActivity.this,RaceService.class));
		
		
			
    }


	private void initOnClickListeners() {
		
		myKlikListener = new OnClickListener() {
				
				public void onClick(View v) {
					
					if(raceButton.getText().equals("done")){
						showScore();  //hack TODO remove
					};
					
					raceTrackView.checkSum();
					raceButton.setText(raceTrackView.getSumStr());			
				}
			};
			
		myStartListener = new OnClickListener() {
				
				public void onClick(View v) {
					
					
					raceTrackView.startRace();					
					raceButton.setOnClickListener(myKlikListener);
					raceButton.setText(raceTrackView.getSumStr());
					
				
					
					
				}
			};
		
	}


	protected void showScore() {
		
		Intent showScores = new Intent(this, ScoreActivity.class);
		startActivity(showScores);
		
	}
	


}
