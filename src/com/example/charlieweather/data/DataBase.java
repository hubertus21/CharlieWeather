package com.example.charlieweather.data;

import java.util.ArrayList;
import java.util.List;

import android.graphics.PointF;

public class DataBase {
	private static DataBase instance = null;
	private List<CityInfo> list = new ArrayList<CityInfo>();
	private PointLD[] cities = new PointLD[5];

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
		String[] urls=new String[cities.length];
		for(int i=0;i<urls.length;i++){
			urls[i]=Formats.START_ADDRESS+cities[i].getLat()+Formats.ADDRESS_LON+cities[i].getLon()+Formats.ADDRESS;
			System.out.println(urls[i]);}
		return urls;
	}
	public void setCords(){
		cities[0]=new PointLD(35,139);
		cities[1]=new PointLD(-0.12574,51.50853);
		cities[2]=new PointLD(-0.12574,51.50853);
		cities[3]=new PointLD(-0.12574,51.50853);
		cities[4]=new PointLD(-0.12574,51.50853);
	}
}
