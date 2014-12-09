package com.example.storageandservices.alarmclock;

import com.example.storageandservices.R;
import com.example.storageandservices.R.id;
import com.example.storageandservices.R.layout;
import com.example.storageandservices.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class AlarmHandler extends Activity {
	private MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
	            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
	            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
	            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		   //startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=3YOYlgvI1uE")));
		mediaPlayer = MediaPlayer.create(this, R.raw.fire);
		mediaPlayer.start();
		
		
	}
	
    public void onClick(View view) {
    	Intent intent = new Intent();
    	
		switch (view.getId()) {
	        case R.id.button_stop_alarm:
	        	mediaPlayer.stop();
	    		setResult(RESULT_OK, intent);
		        this.finish();
		        return;
		}
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm_handler, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
