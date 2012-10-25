package com.example.theNewBoston;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyBringBack extends View {

	Bitmap gBall;
	float changingY;
	Typeface font;
	
	public MyBringBack(Context context) {
		super(context);
		
		gBall = BitmapFactory.decodeResource(getResources(), R.drawable.plusselected);
		changingY = 0;
		
		font = Typeface.createFromAsset(context.getAssets(), "G-Unit.TTF");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawColor(Color.WHITE);
		
		// Handle Font and text
		Paint textPaint = new Paint();
		textPaint.setARGB(150, 254, 100, 200);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(50);
		textPaint.setTypeface(font);
		canvas.drawText("I am the DON don don DON... ", canvas.getWidth()/2, 200, textPaint);
		
		// Draw Bitmap from the resource
		canvas.drawBitmap(gBall, canvas.getWidth()/2, changingY, null);		
		if (changingY < canvas.getHeight()) {
			changingY += 10;
		} else {
			changingY = 0;
		}
		
		// Draw Rectangle
		Rect middleRect = new Rect();
		middleRect.set(0, 400, canvas.getWidth(), 800);
		Paint ourBlue = new Paint();
		ourBlue.setColor(Color.BLUE);
		canvas.drawRect(middleRect, ourBlue);
		
		// Invalidate the whole view
		invalidate();
	}
	
	
}
