package com.example.storageandservices;

import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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

	  public void createTask(TodoCountry todoshit) {
	    ContentValues values = new ContentValues();
	    values.put(CountryDbHelper.COLUMN_TASK, todoshit.getTask());
	    values.put(CountryDbHelper.COLUMN_YEAR, todoshit.getYear());
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
	  
	  public boolean updateTask(TodoCountry tc) {
		  ContentValues args = new ContentValues();
		  args.put(CountryDbHelper.COLUMN_TASK, tc.getTask());
		  args.put(CountryDbHelper.COLUMN_YEAR, tc.getYear());
		  String restrict = CountryDbHelper.COLUMN_ID + "=" + tc.getId();
		  return database.update(CountryDbHelper.TASKS_TABLE_NAME, args, restrict , null) > 0;
	  } 

	  public List<TodoCountry> getAllTasks() {
	    List<TodoCountry> tasks = new ArrayList<TodoCountry>();

	    Cursor cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
	        allColumns, null, null, null, null, null);

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
		  task.setId(cursor.getLong(0));
		  task.setYear(cursor.getString(1));
		  task.setTask(cursor.getString(2));
		  
		  return task;
	  }
	} 


