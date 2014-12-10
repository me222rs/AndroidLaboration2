package com.example.storageandservices;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CountryAdapter extends BaseAdapter{
	private Context context;
	private List<TodoCountry> countries;
	private int textColor;
	private int textSize;
	private int backgroundColor;
	

	public CountryAdapter(Context context, List<TodoCountry> countries) {
		this.context = context;
		this.countries = countries;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row;
		
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.layout_row, parent, false);
		} else {
			row = convertView;
		}
		
		TextView arg1 = (TextView) row.findViewById(R.id.label);
		//TextView arg2 = (TextView) row.findViewById(R.id.label2);
		
		arg1.setText((this.countries.get(position).getYear()).toString());
		//arg2.setText(this.countries.get(position).getTask());
		
		if (this.backgroundColor != 0) {
			arg1.setBackgroundColor(this.backgroundColor);
			//arg2.setBackgroundColor(this.backgroundColor);
		}
		
		if (this.textColor != 0) {
			arg1.setTextColor(this.textColor);
			//arg2.setTextColor(this.textColor);
		}
		
		if (this.textSize != 0) {
			arg1.setTextSize(this.textSize);
			//arg2.setTextSize(this.textSize);
		}
		
		return row;
	}

	@Override
	public int getCount() {
		return this.countries.size();
	}

	@Override
	public Object getItem(int position) {
		return this.countries.get(position);
	}

	@Override
	public long getItemId(int position) {
		return this.countries.indexOf(getItem(position));
	}
	
	public void setBackgroundColor(int color) {
		this.backgroundColor = color;
	}
	
	public int getBackgroundColor() {
		return this.backgroundColor;
	}
	
	public void setTextColor(int color) {
		this.textColor = color;
	}
	
	public int getTextColor() {
		return this.textColor;
	}
	
	public void setTextSize(int size) {
		this.textSize = size;
	}
	
	public int getTextSize() {
		return this.textSize;
	}
}
