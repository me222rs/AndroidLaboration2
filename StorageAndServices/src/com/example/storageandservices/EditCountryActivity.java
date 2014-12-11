package com.example.storageandservices;

import java.util.Calendar;
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
import android.widget.Toast;

public class EditCountryActivity extends Activity {


	CountriesDataSource datasource;
	private List<TodoCountry> values;
	private ArrayAdapter<TodoCountry> listAdapter;
	EditText editTextCountry;
	EditText editTextYear;
	private int id;
    private String task;
    private String year;
    boolean validate;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_country);
		
		datasource = new CountriesDataSource(this);
		
    	editTextCountry = (EditText)findViewById(R.id.country);
    	editTextYear = (EditText)findViewById(R.id.year);
    	
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        
        this.id = bundle.getInt("id");
        this.task = bundle.getString("task");
        this.year = bundle.getString("year");
        
        
        Log.i("EditCountryActivity", "EditCountryActivity.onCreate() — id = " + id);
    	//VisitedCountriesRevisitedActivity country = new VisitedCountriesRevisitedActivity();
    	//TodoCountry tc = country.getValues();
    	editTextCountry.setText(task);
    	editTextYear.setText(year);
    	
	}

	
	public void onClick(View view) {
        // handles add button click
        if (view.getId() == R.id.add) {
        	TodoCountry todoshit = new TodoCountry();
        	todoshit.setId(this.id);
        	todoshit.setTask(editTextCountry.getText().toString().trim());
        	todoshit.setYear(editTextYear.getText().toString().trim());
        	
        	validation(editTextCountry.getText().toString(), editTextYear.getText().toString());
        	
        	
        	//String year = editTextYear.getText().toString().trim();
        	if (validate == true) {
        		// Save the new task to the database
        		//TodoCountry task = datasource.createTask(todo);
        		datasource.updateTask(todoshit);
        		//listAdapter.notifyDataSetChanged();
        		//Log.v(TAG, "index=" + i);
        		Intent intent = new Intent(this, VisitedCountriesRevisitedActivity.class);
        		VisitedCountriesRevisitedActivity vcra = new VisitedCountriesRevisitedActivity();
        		vcra.finish();
        		
        		
        		startActivity(intent);
        		this.finish();
        	}
        	else{
        		Toast.makeText(getApplicationContext(), "Country can not be empty or contain numbers and year must be a number",
     				   Toast.LENGTH_LONG).show();
        	}
        
        }
      }
	
	
	
	private void validation(String country, String year) {
		// TODO Auto-generated method stub
  	Calendar c = Calendar.getInstance(); 
    	
    	int currentYear = c.get(Calendar.YEAR);
    	int intYear = Integer.parseInt(year);
    	
    	
    	if(year.length() > 4 || country.length() == 0 || country == null 
    	|| country.matches(".*\\d.*") || year.length() == 0 || year == null 
    	|| !year.matches("[0-9]+") || intYear > currentYear || country.length() > 40){
    		validate = false;
    	}
    	else{
    		validate = true;
    	}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_country, menu);
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
