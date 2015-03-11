package com.example.charlieweather.data;

import java.util.ArrayList;
import java.util.List;

import com.example.charlieweather.R;

public class CityInfo {

	private List<ForecastForOneDay> list=new ArrayList<ForecastForOneDay>();
	private Long ID;
	private String name="asd";
	private PointLD coords;
	private String country="asd";
	
	
	public List<ForecastForOneDay> getList() {
		return list;
	}
	public void setList(List<ForecastForOneDay> list) {
		this.list = list;
	}
	public void addForecastForOneDay(ForecastForOneDay f){
		list.add(f);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PointLD getCoords() {
		return coords;
	}
	public void setCoords(PointLD coords) {
		this.coords = coords;
	}
	public void setCoords(String lat,String lon) {
		this.coords=new PointLD(Double.valueOf(lat),Double.valueOf(lon));
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public void setCoords(double lat, double lon) {
		this.coords = new PointLD(lat,lon);
		
	}
	public String toString(){
		String s="=================================================\n";
		s+=Helper.context.getString(R.string.nameOfCity)+name+"\n";
		s+=Helper.context.getString(R.string.state)+country+"\n";
		for(int i=0;i<list.size();i++)
			s+=list.get(i).toString()+"\n";
		return s;
	}
	
}
