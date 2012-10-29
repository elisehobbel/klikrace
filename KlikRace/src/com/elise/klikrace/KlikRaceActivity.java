package com.elise.klikrace;


import com.example.klikrace.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class KlikRaceActivity extends Activity  {

    private Button startButton;
    
	private RaceTrackView raceTrackView;

	private OnClickListener myKlikListener;

	private OnClickListener myStartListener;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klik_race);
        
        initOnClickListeners();
        
    	startButton = (Button) findViewById(R.id.button1);
		startButton.setText("Start");
		
		
		startButton.setOnClickListener(myStartListener);	
		
		raceTrackView = new RaceTrackView(this);
		
		FrameLayout layout = (FrameLayout) findViewById(R.id.myFrame);

		layout.addView(raceTrackView);

		startButton.bringToFront();
		
	
		
    }


	private void initOnClickListeners() {
		
		myKlikListener = new OnClickListener() {
				
				public void onClick(View v) {
					
					
				}
			};
			
		myStartListener = new OnClickListener() {
				
				public void onClick(View v) {
					
					raceTrackView.startRace();
					
					startButton.setOnClickListener(myKlikListener);
					startButton.setText(raceTrackView.getSomStr());
				}
			};
		
	}


}
