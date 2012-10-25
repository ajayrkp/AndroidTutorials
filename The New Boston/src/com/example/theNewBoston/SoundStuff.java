package com.example.theNewBoston;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity 
				implements OnClickListener, OnLongClickListener{

	SoundPool sp;
	int explosion = 0;
	MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View v = new View(this);
		v.setOnClickListener(this);
		v.setOnLongClickListener(this);
		setContentView(v);
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		explosion = sp.load(this, R.raw.bomb, 1);
		mp = MediaPlayer.create(this, R.raw.doomed_to_immortality);
	}

	@Override
	public void onClick(View v) {
		if (explosion != 0)
			sp.play(explosion, 1, 1, 0, 0, 1);
	}

	@Override
	public boolean onLongClick(View v) {
		mp.start();
		return false;
	}

	@Override
	protected void onStop() {
		super.onStop();
		sp.stop(1);
		mp.stop();
	}

}
