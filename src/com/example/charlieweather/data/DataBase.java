package com.example.charlieweather.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import SQL.CityDataSource;

public class DataBase {
	private static DataBase instance = null;
	private List<CityInfo> list = new ArrayList<CityInfo>();
	private List<PointLD> cities = new ArrayList<PointLD>();
	private Date date;
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

	public void addNewCity(String name){
		cities.add(new PointLD(name));
	}
	public void addNewCity(double lat,double lon){
		cities.add(new PointLD(lat,lon));
	}
	public void removeCity(int i){
		cities.remove(i);
	}
	public void setList(List<CityInfo> list) {
		this.list = list;
	}
	public String[] getURL(){
		date=Calendar.getInstance().getTime();
		String[] urls=new String[cities.size()];
		for(int i=0;i<urls.length;i++){
			if(cities.get(i).getCityname()!="null")
				urls[i]=Formats.ADDRESS_FROM_NAME+cities.get(i).getCityname();
			else
			urls[i]=Formats.START_ADDRESS+cities.get(i).getLat()+Formats.ADDRESS_LON+cities.get(i).getLon()+Formats.ADDRESS;
			System.out.println(urls[i]);}
		return urls;
	}
	
	public void setCords(CityDataSource dataSource){
		List<CityInfo> l= dataSource.getAllInfo();
		cities.clear();
		if(l.size()>0){
			for(int i=0;i<l.size();i++)
				cities.add(new PointLD(l.get(i).getCountry()));
		}else{
		cities.add(new PointLD("Kraków"));
		cities.add(new PointLD("Warszawa"));
		cities.add(new PointLD("Berlin"));
		cities.add(new PointLD("London"));
		cities.add(new PointLD(-0.12574,51.50853));}
	}
	public String getAllInfoInString(){
		String s="";
		for(int i=0;i<list.size();i++)
			s+=getInfoInString(i)+"\n";
		return s;
	}
	public String getInfoInString(int i){
		if(i<list.size()&& i>=0)
			return list.get(i).toString();
		else
			return "";
	}
}
