package com.example.theNewBoston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class Graphix extends Activity {

	MyBringBack ourView;
	WakeLock wL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// Setup WakeLock
		PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");

		super.onCreate(savedInstanceState);
		
		wL.acquire(); // start wakeLock
		
		ourView = new MyBringBack(this);
		setContentView(ourView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		// Release wakeLock
		wL.release(); 
	}

	
	
}
