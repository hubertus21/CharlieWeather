package com.example.charlieweather;

import android.provider.MediaStore.Images;

public class Weather {
	private Images image;
	private String temperature;
	private String day;
	private String date;
	public Images getImage() {
		return image;
	}
	public void setImage(Images image) {
		this.image = image;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
