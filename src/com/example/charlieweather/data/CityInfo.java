package com.example.charlieweather.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CityInfo {

	private List<ForecastForOneDay> list=new ArrayList<ForecastForOneDay>();
	private int ID;
	private String name;
	private PointLD coords;
	private String country;
	
	
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
	public void setCoords(BigDecimal lat,BigDecimal lon) {
		this.coords = new PointLD(lat,lon);
	}
	public void setCoords(String lat,String lon) {
		
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	
}
