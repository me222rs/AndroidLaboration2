package com.example.storageandservices;

import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CountriesDataSource {

	  // Database fields
	  private SQLiteDatabase database;
	  private CountryDbHelper dbHelper;
	  private String[] allColumns = { CountryDbHelper.COLUMN_ID,
			  CountryDbHelper.COLUMN_YEAR, CountryDbHelper.COLUMN_TASK };

	  public CountriesDataSource(Context context) {
	    dbHelper = new CountryDbHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public void createTask(TodoCountry countries) {
	    ContentValues values = new ContentValues();
	    values.put(CountryDbHelper.COLUMN_TASK, countries.getTask());
	    values.put(CountryDbHelper.COLUMN_YEAR, countries.getYear());
	    long insertId = database.insert(CountryDbHelper.TASKS_TABLE_NAME, null, values);
	    Cursor cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
	        allColumns, CountryDbHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    TodoCountry newTask = cursorToTask(cursor);
	    cursor.close();
	    //return newTask;
	  }

	  
	  public void deleteTask(TodoCountry task) {
	    long id = task.getId();
	    database.delete(CountryDbHelper.TASKS_TABLE_NAME, CountryDbHelper.COLUMN_ID
	    		+ " = " + id, null);
	  }

	  /*
	  public void deleteTask(int year, String country) {
	    	String[] whereArgs = new String[2];
	    	whereArgs[0] = year+"";
	    	whereArgs[1] = country;
	    	//Sql to delete a post
	    	database.delete(CountryDbHelper.TASKS_TABLE_NAME, CountryDbHelper.COLUMN_YEAR + " = ? AND " + 
	    			CountryDbHelper.COLUMN_TASK + " = ? ", whereArgs);
		  }
	  */
	  public TodoCountry getTask(long taskId) {
		  String restrict = CountryDbHelper.COLUMN_ID + "=" + taskId;
		  Cursor cursor = database.query(true, CountryDbHelper.TASKS_TABLE_NAME, allColumns, restrict, 
		    		                        null, null, null, null, null);
		  if (cursor != null && cursor.getCount() > 0) {
			  cursor.moveToFirst();
			  TodoCountry task = cursorToTask(cursor);
			  return task;
		  }
		  // Make sure to close the cursor
		  cursor.close();
		  return null;
	  }
	  
	  public void updateTask(TodoCountry tc) {
		  open();
		  Log.i("EditCountryActivity", "CountriesDataSource.updateTask() — id = " + tc.getYear());
		  ContentValues args = new ContentValues();
		  args.put(CountryDbHelper.COLUMN_TASK, tc.getTask());
		  args.put(CountryDbHelper.COLUMN_YEAR, tc.getYear());
		  String restrict = CountryDbHelper.COLUMN_ID + "=" + tc.getId();
		  
		  Log.i("EditCountryActivity", "CountriesDataSource.updateTask() — bool is = " + database);
		  
		  database.update(CountryDbHelper.TASKS_TABLE_NAME, args, restrict , null);
		  //Log.i("EditCountryActivity", "CountriesDataSource.updateTask() — bool is = " + i);
		  
		  database.close();
		  //return i;
	  } 
	/*  
	  public List<String> getAllTasks(String orderBy) {
		    List<String> tasks = new ArrayList<String>();

		    Cursor cursor;
		    if(orderBy.equals("nameDESC")){
		    cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
		        allColumns, null, null, null, null, dbHelper.COLUMN_TASK + " DESC");
		    } 
		    else if(orderBy.equals("yearDESC")){
		    cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
			    allColumns, null, null, null, null, dbHelper.COLUMN_YEAR + " DESC");
		    }
		    else if(orderBy.equals("yearASC")){
			    cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
					    allColumns, null, null, null, null, dbHelper.COLUMN_YEAR + " ASC");
		    }
		    else if(orderBy.equals("nameASC")){
			    cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
					    allColumns, null, null, null, null, dbHelper.COLUMN_TASK + " ASC");
		    }
		    else{
			    cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
					    allColumns, null, null, null, null, null);
		    }
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	//TodoCountry task = cursorToTask(cursor);
		    	tasks.add(cursor.getInt(0) + "  " + cursor.getString(1));
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return tasks;
		  }
	  */

	  public List<TodoCountry> getAllTasks(String orderBy) {
	    List<TodoCountry> tasks = new ArrayList<TodoCountry>();

	    Cursor cursor;
	    if(orderBy.equals("nameDESC")){
	    cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
	        allColumns, null, null, null, null, dbHelper.COLUMN_TASK + " DESC");
	    } 
	    else if(orderBy.equals("yearDESC")){
	    cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
		    allColumns, null, null, null, null, dbHelper.COLUMN_YEAR + " DESC");
	    }
	    else if(orderBy.equals("yearASC")){
		    cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
				    allColumns, null, null, null, null, dbHelper.COLUMN_YEAR + " ASC");
	    }
	    else if(orderBy.equals("nameASC")){
		    cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
				    allColumns, null, null, null, null, dbHelper.COLUMN_TASK + " ASC");
	    }
	    else{
		    cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
				    allColumns, null, null, null, null, null);
	    }
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	TodoCountry task = cursorToTask(cursor);
	      tasks.add(task);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return tasks;
	  }

	  private TodoCountry cursorToTask(Cursor cursor) {
		  TodoCountry task = new TodoCountry();
		  task.setId(cursor.getInt(0));
		  task.setYear(cursor.getString(1));
		  task.setTask(cursor.getString(2));
		  
		  return task;
	  }
	} 


