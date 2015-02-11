package com.example.charlieweather.data;


public class Temperature {

	private double day;
	private double min;
	private double max;
	private double night;
	private double eve;
	private double morning;
	private Long ID,IDOwner;
	
	public double getDay() {
		return day;
	}
	public void setDay(double day) {
		this.day = day;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getNight() {
		return night;
	}
	public void setNight(double night) {
		this.night = night;
	}
	public double getEve() {
		return eve;
	}
	public void setEve(double eve) {
		this.eve = eve;
	}
	public double getMorning() {
		return morning;
	}
	public void setMorning(double morning) {
		this.morning = morning;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public Long getIDOwner() {
		return IDOwner;
	}
	public void setIDOwner(Long iDOwner) {
		IDOwner = iDOwner;
	}
	
}
