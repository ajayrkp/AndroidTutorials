package com.example.theNewBoston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener, OnCheckedChangeListener {

	TextView question, test;
	Button returnData;
	RadioGroup selectionList;
	String gotBread, sendData;
	String et, values;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		et = getData.getString("name", "I am Don...");
		values = getData.getString("list", "4");
		
		if (values.contains("1")){
			question.setText(et);
		}
		//getData();
	}

	private void getData() {
		Bundle gotBasket = getIntent().getExtras();
		gotBread = gotBasket.getString("key");
		
		question.setText(gotBread);
		
	}

	private void initialize() {
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvText);
		returnData = (Button) findViewById(R.id.bReturn);
		selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
		returnData.setOnClickListener(this);
		selectionList.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent personIntent = new Intent();
		Bundle backpack = new Bundle();
		backpack.putString("answer", sendData);
		personIntent.putExtras(backpack);
		setResult(RESULT_OK, personIntent);
		finish();

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rCrazy:
			sendData = "Probably right!";
			break;
		case R.id.rSexy:
			sendData = "Definitely right!";
			break;
		case R.id.rBoth:
			sendData = "Spot on!";
			break;
		}
		test.setText(sendData);
	}

}
