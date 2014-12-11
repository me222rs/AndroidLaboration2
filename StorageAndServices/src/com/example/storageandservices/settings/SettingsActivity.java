package com.example.storageandservices.settings;

import java.util.List;

import com.example.storageandservices.AddCountryActivity;
import com.example.storageandservices.R;
import com.example.storageandservices.R.xml;
import com.example.storageandservices.VisitedCountriesRevisitedActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings.TextSize;

public class SettingsActivity extends PreferenceActivity {
//http://developer.android.com/reference/android/preference/PreferenceActivity.html
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	
	
	@SuppressLint("NewApi")
	@Override
	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.setting, target);
	}
	
	@Override//Needs to be overrided for some security reasons in api 19
    protected boolean isValidFragment(String fragmentName) {
		
    	  return BackgroundColor.class.getName().equals(fragmentName) 
    		  || TextSizeActivity.class.getName().equals(fragmentName);
    	  
    	  
	}
}
