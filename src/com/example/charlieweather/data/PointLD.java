package com.example.charlieweather.data;

import java.math.BigDecimal;

public class PointLD {

	private BigDecimal lat,lon;
	private String cityname;
	public PointLD(BigDecimal lat, BigDecimal lon) {
		this.lat=lat;
		this.lon=lon;
	}

	public PointLD(double d, double e) {
		lat=BigDecimal.valueOf(d);
		lon=BigDecimal.valueOf(e);
	}
	public PointLD(String a){
		cityname=a;
	}
	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}
	
	

}
