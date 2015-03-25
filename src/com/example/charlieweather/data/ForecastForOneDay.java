package com.example.charlieweather.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.charlieweather.R;

public class ForecastForOneDay {

	private Long ID;
	private Long ID_owner;
	private Date date=new Date();
	private String imageUrl;
	private Temperature temperature=new Temperature();
	private String mainWheater="";
	private String description="";
	private double pressure=0;
	private double speed=0;
	private double deg=0;
	private int clouds=0;
	private int humidity=-1;
	private double snow=0;
	private double rain=0;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setDateUnix(String unixTime){
		long dv = Long.valueOf(unixTime)*1000;
		date = new java.util.Date(dv);
		System.out.println(new SimpleDateFormat(Formats.DATE_FORMAT).format(date));
	}
	public String getDateString(){
		return new SimpleDateFormat(Formats.DATE_FORMAT).format(date);
	}
	public Long getUnixDate(){
		return date.getTime()/1000;
	}
	public void setDateFromString(String dtStart){
		SimpleDateFormat  format = new SimpleDateFormat(Formats.DATE_FORMAT);  
		    Date date;
			try {
				date = format.parse(dtStart);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Temperature getTemperature() {
		return temperature;
	}
	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}
	public String getMainWheater() {
		return mainWheater;
	}
	public void setMainWheater(String mainWheater) {
		this.mainWheater = mainWheater;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDeg() {
		return deg;
	}
	public void setDeg(double deg) {
		this.deg = deg;
	}
	public int getClouds() {
		return clouds;
	}
	public void setClouds(int clouds) {
		this.clouds = clouds;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public double getSnow() {
		return snow;
	}
	public void setSnow(double snow) {
		this.snow = snow;
	}
	public double getRain() {
		return rain;
	}
	public void setRain(double rain) {
		this.rain = rain;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public Long getID_owner() {
		return ID_owner;
	}
	public void setID_owner(Long iD_owner) {
		ID_owner = iD_owner;
	}
	@Override
	public String toString() {
		String s="\n";
		s+=getDateString()+"\n";
		s+=description+"\n";
		s+=temperature.toString();
		if(humidity!=-1)
			s+=Helper.context.getString(R.string.humidity)+humidity+" %\n";
		s+=Helper.context.getString(R.string.pressure)+pressure+" hPa\n";
		if(snow!=0)
			s+=Helper.context.getString(R.string.snow)+snow+" mm\n";
		if(rain!=0)
			s+=Helper.context.getString(R.string.rain)+rain+" mm\n";
		if(clouds!=0)
			s+=Helper.context.getString(R.string.clouds)+clouds+" %\n";	
		return s;
	}
}
