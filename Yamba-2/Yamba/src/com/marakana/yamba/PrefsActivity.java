package com.marakana.yamba;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PrefsActivity extends PreferenceActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    setTitle(R.string.titlePrefs);
    
    addPreferencesFromResource(R.xml.prefs);
  }

}
