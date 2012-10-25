package com.example.theNewBoston;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TextVoice extends Activity implements OnClickListener {

	static final String[] texts = {
		"Whaaaaaaaaat's up Aanya and Adi..",
		"Can I call you guys Adinya...",
		"My name is Lakhan... sajnaa kaa sajan"
	};
	
	TextToSpeech tts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textvoice);
		Button b = (Button) findViewById(R.id.bTextToVoice);
		b.setOnClickListener(this);
		
		tts = new TextToSpeech(TextVoice.this, new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				if (status != TextToSpeech.ERROR) {
					tts.setLanguage(Locale.UK);
				}
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		Random r = new Random();
		String random = texts[r.nextInt(3)];
		tts.speak(random, TextToSpeech.QUEUE_FLUSH, null);
	}

	@Override
	protected void onPause() {
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
			
		super.onPause();
	}

}
