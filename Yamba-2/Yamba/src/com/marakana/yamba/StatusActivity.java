package com.marakana.yamba;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StatusActivity extends Activity implements OnClickListener {
  EditText editStatus;
  Button buttonUpdate;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//    Debug.startMethodTracing("Yamba.trace");

    setContentView(R.layout.status);

    editStatus = (EditText) findViewById(R.id.editStatus);
    buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

    buttonUpdate.setOnClickListener(this);
  }

  @Override
  protected void onStop() {
    super.onStop();

//    Debug.stopMethodTracing();
  }
  
  
  ////////// Button stuff

  public void onClick(View v) {

    String status = editStatus.getText().toString();

     new PostToTwitter().execute(status);

    Log.d("StatusActivity", "onClick'd with status: " + status);
  }
  
  ///////// Menu Stuff
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);    
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    
    switch(item.getItemId()) {
      case R.id.itemPrefs:
        startActivity( new Intent(this, PrefsActivity.class) );
        break;
    }
    
    return true;
  }
  
  
  ///////// Posting to twitter stuff
 
  private class PostToTwitter extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground(String... status) {
      String result;
      // Update status online
      try {
        Twitter twitter = new Twitter("learningandroid", "pass2010");
        twitter.setAPIRootUrl("http://learningandroid.status.net/api");
        twitter.setStatus(status[0]);
        result = StatusActivity.this.getString(R.string.msgStatusUpdatedSuccessfully);
      } catch (TwitterException e) {
        e.printStackTrace();
        result = StatusActivity.this.getString(R.string.msgStatusUpdateFailed);
      }

      return result;
    }

    @Override
    protected void onPostExecute(String result) {
      super.onPostExecute(result);
      
      Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
    }    
  }

}