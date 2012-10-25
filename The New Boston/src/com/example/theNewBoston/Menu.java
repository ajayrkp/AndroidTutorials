package com.example.theNewBoston;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	String classes[] = { "StartingPoint", "TextPlay", "Email", 
			"Camera", "Data", "Graphix", "GraphixSurface", "SoundStuff",
			"Slider", "Tabs", "SimpleBrowser", "Flipper", "SharedPrefs", 
			"InternalData", "ExternalData", "SQLiteExample", "Accelerate",
			"HttpExample", "WeatherXmlParsing", "GLExample", "GLCubeEx",
			"Voice", "TextVoice", "StatusBar", "SeekBarVolume", "Adinya-finish" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set to full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setListAdapter(new ArrayAdapter<String>
			(Menu.this, android.R.layout.simple_list_item_1, classes));
	
	
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String classClicked = classes[position];
		Class ourClass;
		try {
			ourClass = Class.forName("com.example.theNewBoston." + classClicked);
			Intent ourIntent = new Intent(Menu.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.aboutUs:
			Intent i = new Intent("com.example.theNewBoston.ABOUT");
			startActivity(i);
			break;
		case R.id.preferences:
			Intent p = new Intent("com.example.theNewBoston.PREFS");
			startActivity(p);
			break;
			
		case R.id.exit:
			finish();
			break;
		}
		
		return false;
	}


}
