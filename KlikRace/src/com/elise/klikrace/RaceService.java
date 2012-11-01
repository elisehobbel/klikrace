package com.elise.klikrace;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


public class RaceService extends Service {

	private boolean paused = false;
	private long startTime; 
	private volatile Looper myLooper;

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
				Looper.prepare();
			    while(!paused){
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					Log.d("threadtest", "test" + (System.currentTimeMillis() - startTime));
					Toast.makeText(RaceService.this, "service running... ", Toast.LENGTH_LONG).show(); //TODO FIX this does not show
					//see: http://stackoverflow.com/questions/10403858/java-cant-create-handler-inside-thread-that-has-not-called-looper-prepare
			    }
			    
			    myLooper = Looper.myLooper();
			    Looper.loop();
			}
			
			
		});
		
		testThread.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "service destroyed", Toast.LENGTH_LONG).show();
		paused  = true;
		myLooper.quit();
		
	}

	
}
