package com.example.storageandservices;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class VisitedCountriesRevisitedActivity extends Activity {

	private CountriesDataSource datasource;
	ArrayList<String> list;
	//private List<String> values;
	private List<TodoCountry> values;
	public ArrayAdapter<TodoCountry> listAdapter;
	//public ArrayAdapter<String> listAdapter;
	CountriesDataSource cds;
	private int clickedItem;
	private TodoCountry tc;
	private String orderBy;
	SharedPreferences prefs;
	View colorLayout;
	String color;
	TextView size;
	String textSize;
	
	ListView countrylist;
	List<TodoCountry> countries;
	
	
	ListView listview;
	
	@SuppressLint("ViewHolder")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		setContentView(R.layout.activity_visited_countries_revisited);
		
		listview = (ListView) findViewById(R.id.list);
        datasource = new CountriesDataSource(this);
        datasource.open();

        orderBy = prefs.getString("sortBy", "yearASC");
        // get all tasks
        //orderBy = "nameASC";
        
        
        values = (ArrayList<TodoCountry>) datasource.getAllTasks(orderBy);

        
        listview.setAdapter(listAdapter = new ArrayAdapter<TodoCountry>(this, R.layout.layout_row, values){
        	
        	@Override
		    public View getView(int position, View row, ViewGroup parent) {
        		registerForContextMenu(listview);
        		System.out.println(listAdapter);
        		TodoCountry data = getItem(position);
		    	row = getLayoutInflater().inflate(R.layout.layout_row, parent, false);
		    	size = (TextView) row.findViewById(R.id.label);
		    	textSize = prefs.getString("sizePref", "2");
		    	
		    	if(textSize.equals("1")) {
					size.setTextSize(12);
				} 
		    	else if(textSize.equals("2")) {
					size.setTextSize(20);
				} 
		    	else if(textSize.equals("3")) {
					size.setTextSize(35);
				}		    	
		    	size.setText(data.toString());
		        return size;
        	}
        });
        
        
        colorLayout = findViewById(R.id.layout);		
		color = prefs.getString("colorPref", "1");
		setBackgroundColor(color);
        
      
	}
	
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;
		clickedItem = acmi.position;
		
		TodoCountry item = listAdapter.getItem(clickedItem);
		menu.setHeaderTitle("Edit or delete" + item);
		menu.add(1,1,1,"Edit");
		menu.add(1,2,2, "Delete");
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		
		// TODO Auto-generated method stub
		tc = new TodoCountry();
		String title = (String) item.getTitle();
		
		
		TodoCountry country = listAdapter.getItem(clickedItem);
		
		
		tc.setId(country.getId());
		tc.setTask(country.getTask());
		tc.setYear(country.getYear());
		
		if(title.equals("Edit")) {
			editCountry(tc);
		} else if(title.equals("Delete")){
			datasource.deleteTask(tc);
            Intent productIntent = new Intent(this,VisitedCountriesRevisitedActivity.class);
            startActivity(productIntent);
            this.finish();
		}
		return true;
	}
	
	public void editCountry(TodoCountry tc) {
		TodoCountry country = listAdapter.getItem(clickedItem);
		tc.setId(country.getId());
		tc.setTask(country.getTask());
		tc.setYear(country.getYear());
		Log.i("VisitedCountriesRevisitedActivity", "VisitedCountriesRevisitedActivity.editCountry() � id = " + tc.getId());
		
		Intent editCountryIntent = new Intent(this, EditCountryActivity.class);
		editCountryIntent.putExtra("id", tc.getId());
		editCountryIntent.putExtra("task", tc.getTask());
		editCountryIntent.putExtra("year", tc.getYear());
		startActivity(editCountryIntent);
	}
	
	public TodoCountry getValues(){
		return tc;
	}
    
    
    @Override
    protected void onResume() {
    	datasource.open();
    	color = prefs.getString("colorPref", color);
    	setBackgroundColor(color);
    	textSize = prefs.getString("sizePref", textSize);
    	listAdapter.notifyDataSetChanged();
    	super.onResume();
    }

    @Override
    protected void onPause() {
    	datasource.close();
    	super.onPause();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.visited_countries_revisited, menu);
		return true;
	}
	
	public void sort(String orderBy) {
		listAdapter.clear();
		
		listAdapter.addAll(datasource.getAllTasks(orderBy));

		
        listAdapter.notifyDataSetChanged();
        
	}
	
	public void saveSortBy(String orderBy) {
		prefs.edit().putString("sortBy", orderBy).commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		switch (item.getItemId()) {
    	case R.id.action_settings:
    		Intent settings = new Intent(this, com.example.storageandservices.settings.SettingsActivity.class);
    		startActivity(settings);
    		//this.finish();
    		return true;
    		//return true;
        case R.id.order_by_year:
        	orderBy = "yearDESC";
        	saveSortBy(orderBy);
            sort(orderBy);
            return true;
        case R.id.order_by_name:
        	orderBy = "nameDESC";
        	saveSortBy(orderBy);
        	sort(orderBy);
            return true;
        case R.id.order_by_yearASC:
        	orderBy = "yearASC";
        	saveSortBy(orderBy);
        	sort(orderBy);
            return true;
        case R.id.order_by_nameASC:
        	orderBy = "nameASC";
        	saveSortBy(orderBy);
        	sort(orderBy);
            return true;
        case R.id.action_add:
            Intent productIntent = new Intent(this,AddCountryActivity.class);
            startActivity(productIntent);
            this.finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
		
		
	}
	
	public void setBackgroundColor(String color) { 
		if(color.equals("1")){
			colorLayout.setBackgroundColor(Color.WHITE);
		}
		else if(color.equals("2")) {
			colorLayout.setBackgroundColor(Color.GREEN);
		} 
		else if(color.equals("3")) {
			colorLayout.setBackgroundColor(Color.BLUE);
		} 
		else if(color.equals("4")) {
			colorLayout.setBackgroundColor(Color.MAGENTA);
		} 
		else if(color.equals("5")) {
			colorLayout.setBackgroundColor(Color.YELLOW);
		} 
	}
	
	
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putStringArrayList("values", list);
		outState.putString("colorPref", color);
		outState.putString("sizePref", textSize);
		saveSortBy(orderBy);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		list = savedInstanceState.getStringArrayList("values");
		textSize = savedInstanceState.getString("sizePref");
		color = savedInstanceState.getString("colorPref");
		setBackgroundColor(color);
		super.onRestoreInstanceState(savedInstanceState);
	}
	
}
