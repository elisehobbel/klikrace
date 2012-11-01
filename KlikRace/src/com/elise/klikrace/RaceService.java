package com.elise.klikrace;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class RaceService extends Service {

	private boolean paused = false;
	private long startTime; //TODO WORK IN PROGRESSs

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "service created", Toast.LENGTH_LONG).show();
		paused  = false;
		
		startTime = System.currentTimeMillis();
		
		Thread testThread = new Thread(new Runnable() {
			
			public void run() {
			    while(!paused){
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					Log.d("threadtest", "test");
					//Toast.makeText(RaceService.this, "service running... ", Toast.LENGTH_LONG).show(); //TODO this crashes the app!!!.. needs to be fixed first
			     }
			}
		});
		
		testThread.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "service destroyed", Toast.LENGTH_LONG).show();
		paused  = true;
	}

	
}
