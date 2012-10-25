package com.example.theNewBoston;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GraphixSurface extends Activity implements OnTouchListener {

	MyBringBackSurface ourSurfaceView;
	
	// s - starting, f - finishing d- change
	float x, y, sX, sY, fX, fY;
	float dX, dY, aniX, aniY, scaledX, scaledY; 
	Bitmap test, plus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ourSurfaceView = new MyBringBackSurface(this);
		
		// Set touch listener
		ourSurfaceView.setOnTouchListener(this); 
		x = y = sX = sY = fX = fY = 0;
		dX = dY = aniX = aniY = scaledX  = scaledY = 0;

		
		test = BitmapFactory.decodeResource(getResources(), R.drawable.plusselected);
		plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
		
		setContentView(ourSurfaceView);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		// call the surfaceView function 
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// call the surfaceView function 
		ourSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		try {
			// Sleep once in a while and don't use the whole processing
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get the X and Y values of touch (when screen is touched) 
		x = event.getX();
		y = event.getY();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			dX = dY = aniX = aniY = scaledX  = scaledY = fX = fY = 0;
			break;
			
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			dX = fX - sX;
			dY = fY - sY;
			scaledX = dX/30;
			scaledY = dY/30;
			x = y = 0;
			break;
		}
		return true;
	}

	
	
	public class MyBringBackSurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;
		
		public MyBringBackSurface(Context context) {
			super(context);
			ourHolder = getHolder();
			
		}


		public void pause() {
			isRunning = false; 
			
			// Destroy the currently running thread
			while (true) {
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			
			ourThread = null;
		}
		
		public void resume() {
			isRunning = true;
			
			// Start the thread in Resume class
			ourThread = new Thread(this);
			ourThread.start();
		}
		
		@Override
		public void run() {
			while (isRunning) {
				// If surface is NOT valid, just cotinue for next retry
				if(!ourHolder.getSurface().isValid())
					continue;
				
				// Here is the magic
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(2, 2, 150);
				if (x != 0 && y != 0) {
					canvas.drawBitmap(test, x - (test.getWidth()/2), y - (test.getHeight()/2), null);
				}
					
				if (sX != 0 && sY != 0) {
					canvas.drawBitmap(plus, sX - (plus.getWidth()/2), sY - (plus.getHeight()/2), null);
				}
					
				if (fX != 0 && fY != 0) {
					canvas.drawBitmap(test, fX - (test.getWidth()/2) - aniX, fY - (test.getHeight()/2) - aniY, null);
					canvas.drawBitmap(plus, fX - (plus.getWidth()/2), fY - (plus.getHeight()/2), null);
				}
				aniX += scaledX;
				aniY += scaledY;	
				
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}

		
	}

	
}
