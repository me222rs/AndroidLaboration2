package com.example.storageandservices;

public class TodoCountry {
	  private int id;
	  private String task;
	  private String year;

	  public int getId() {
	    return id;
	  }

	  public void setId(int id) {
	    this.id = id;
	  }

	  public String getTask() {
	    return task;
	  }

	  public void setTask(String task) {
	    this.task = task;
	  }
	  
	  public String getYear() {
		    return year;
		  }

		  public void setYear(String year) {
		    this.year = year;
		  }

	  @Override
	  public String toString() {
	    return year + " " + task;
	  }
}