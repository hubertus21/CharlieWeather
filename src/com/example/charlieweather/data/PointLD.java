package com.example.charlieweather.data;

import java.math.BigDecimal;

public class PointLD {

	private double lat=0.0;
	private double lon=0.0;
	private String cityname="null";
	public PointLD(double lat, double lon) {
		this.lat=lat;
		this.lon=lon;
	}

	public PointLD(String a){
		cityname=a;
	}
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	

}
