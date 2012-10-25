package com.elise.klikrace;


import com.example.klikrace.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class KlikRaceActivity extends Activity implements OnClickListener {

    private Button startButton;
    
	private RaceTrackView raceTrackView;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klik_race);
        
    	startButton = (Button) findViewById(R.id.button1);
		startButton.setText("Start");
		startButton.setOnClickListener(this);	
		
		raceTrackView = new RaceTrackView(this);
		

		FrameLayout layout = (FrameLayout) findViewById(R.id.myFrame);

		layout.addView(raceTrackView);

		startButton.bringToFront();
    }


	public void onClick(View arg0) {
		
		raceTrackView.increasePercentageAndCaptureScore();
		
	}
}
