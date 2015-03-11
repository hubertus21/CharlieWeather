package com.example.charlieweather;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NextDaysAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Weather> list;
	
	
	public NextDaysAdapter(Context context, List<Weather> objects){
		mInflater = LayoutInflater.from(context);
		list = objects;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder;
		if(convertView == null) {
			view = mInflater.inflate(R.layout.next_day_layout, parent, false);
			holder = new ViewHolder();
			holder.day = (TextView)view.findViewById(R.id.dayTextViewNext);
			holder.date = (TextView)view.findViewById(R.id.dateTextViewNext);
			holder.temparature = (TextView)view.findViewById(R.id.temperatureTextViewNext);
			holder.image = (ImageView)view.findViewById(R.id.weatherImageViewNext);
			
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder)view.getTag();
		}

		Weather item = list.get(position);
		//holder.calendar.setText(String.valueOf(item.GetCalendar()));
		holder.day.setText(item.getDay());
		holder.date.setText(item.getDate());
		holder.temparature.setText(item.getTemperature());
		//holder.image.setimage
		

		return view;
	}

	private class ViewHolder {
		public TextView day, date, temparature;
		public ImageView image;
	}

}
