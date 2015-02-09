package com.example.charlieweather.data;

import java.util.ArrayList;
import java.util.List;

import android.graphics.PointF;

public class DataBase {
	private static DataBase instance = null;
	private List<CityInfo> list = new ArrayList<CityInfo>();
	private List<PointLD> cities = new ArrayList<PointLD>();

	public DataBase() {

	}

	public static DataBase getInstance() {
		if (instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	public List<CityInfo> getList() {
		return list;
	}

	public void setList(List<CityInfo> list) {
		this.list = list;
	}
	public String[] getURL(){
		String[] urls=new String[cities.size()];
		for(int i=0;i<urls.length;i++){
			urls[i]=Formats.START_ADDRESS+cities.get(i).getLat()+Formats.ADDRESS_LON+cities.get(i).getLon()+Formats.ADDRESS;
			System.out.println(urls[i]);}
		return urls;
	}
	public void setCords(){
		cities.add(new PointLD(35,139));
		cities.add(new PointLD(50.0467657,20.0048731));
		cities.add(new PointLD(49.9874932,19.7339459));
		cities.add(new PointLD(-0.12574,51.50853));
		cities.add(new PointLD(-0.12574,51.50853));
	}
}
