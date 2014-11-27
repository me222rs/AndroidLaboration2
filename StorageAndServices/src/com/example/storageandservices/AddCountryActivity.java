package com.example.storageandservices;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
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
        	EditText text = (EditText)findViewById(R.id.todo);
        	String todo = text.getText().toString().trim();
        	if (!todo.isEmpty()) {
        		// Save the new task to the database
        		//TodoCountry task = datasource.createTask(todo);
        		datasource.createTask(todo);
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
