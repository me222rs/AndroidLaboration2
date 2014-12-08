package com.example.storageandservices.settings;

import java.util.List;

import com.example.storageandservices.R;
import com.example.storageandservices.R.xml;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings.TextSize;

public class SettingsActivity extends PreferenceActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@SuppressLint("NewApi")
	@Override
	public void onBuildHeaders(List<Header> target) {
		//Loads the location_preferences from the XML folder
		loadHeadersFromResource(R.xml.setting, target);
	}
	
	@Override//Have to do this cause of some changes in api 19
    protected boolean isValidFragment(String fragmentName) {
    	  return BackgroundColor.class.getName().equals(fragmentName) 
    		  || TextSizeActivity.class.getName().equals(fragmentName);
	}
}
