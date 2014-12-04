package com.example.storageandservices;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class AddCountryActivity extends Activity {

	private CountriesDataSource datasource;
	private List<TodoCountry> values;
	private ArrayAdapter<TodoCountry> listAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_country);
		
		
        datasource = new CountriesDataSource(this);
        datasource.open();
	}
	
    public void onClick(View view) {
        // handles add button click
        if (view.getId() == R.id.add) {
        	TodoCountry todoshit = new TodoCountry();
        	EditText editTextCountry = (EditText)findViewById(R.id.country);
        	EditText editTextYear = (EditText)findViewById(R.id.year);
        	todoshit.setTask(editTextCountry.getText().toString().trim());
        	todoshit.setYear(editTextYear.getText().toString().trim());
        	//String year = editTextYear.getText().toString().trim();
        	if (true) {
        		// Save the new task to the database
        		//TodoCountry task = datasource.createTask(todo);
        		datasource.createTask(todoshit);
        		//listAdapter.notifyDataSetChanged();
        		//Log.v(TAG, "index=" + i);
        		Intent intent = new Intent(this, VisitedCountriesRevisitedActivity.class);
        		VisitedCountriesRevisitedActivity vcra = new VisitedCountriesRevisitedActivity();
        		vcra.finish();
        		
        		
        		startActivity(intent);
        		this.finish();
        		//listAdapter.add(task);
                //listAdapter.notifyDataSetChanged();
        	}
        
        }
      }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_country, menu);
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
	
    
    @Override
    protected void onResume() {
      datasource.open();
      super.onResume();
    }

    @Override
    protected void onPause() {
      datasource.close();
      super.onPause();
    }
}
