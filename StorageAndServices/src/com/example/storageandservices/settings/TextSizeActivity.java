package com.example.storageandservices.settings;

import com.example.storageandservices.R;
import com.example.storageandservices.R.id;
import com.example.storageandservices.R.layout;
import com.example.storageandservices.R.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuItem;

@SuppressLint("NewApi")
public class TextSizeActivity extends PreferenceFragment {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.activitytextsize);
    }
}