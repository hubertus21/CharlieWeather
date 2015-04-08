package com.example.charlieweather;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charlieweather.data.ForecastForOneDay;
import com.example.charlieweather.data.Helper;



public class CityWeather extends Activity {
	
	private ForecastForOneDay forecast;
	private TextView temperatureView;
	private TextView weatherDescription;
	private ListView weatherDetailsList;
	
	public void onCeate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_city_weather);
		setup();
	}
	
	public void onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_city_weather, container,false);
		setContentView(R.layout.activity_main);
				setup();
				//return rootView;
	    }
	    

	private void setup() {
		temperatureView = (TextView)this.findViewById(R.id.temperatureViewOnly);
		weatherDescription = (TextView)findViewById(R.id.weatherDetailsListOnly);
		weatherDetailsList = (ListView)findViewById(R.id.weatherDescriptionOnly);
		img = (ImageView)findViewById(R.id.weatherDetailsListOnly);
		forecast = City.newActivityForecast;
		
		String temperature = forecast.getTemperature().toString();
		temperatureView.setText(temperature);
		
		setDetails(forecast);
		weatherDescription.setText(forecast.getDescription().toString());
		
		//new LoadImage().execute(forecast.getImageUrl());
		
	}
	
	
	private void setDetails(ForecastForOneDay _forecast) {
		String[] values = new String[5];
		values[0] = "Deszcz: "+Double.toString(_forecast.getRain())+" mm";
		values[0] = "�nieg: "+Double.toString(_forecast.getSnow())+" mm";
		values[2] = "Wiatr: "+Double.toString(_forecast.getSpeed()) +" km/h";
		values[3] = "Wilgotno��: "+Integer.toString(_forecast.getHumidity())+" %";
		values[4] = "�i�nienie : "+Double.toString(_forecast.getPressure())+" hPa";
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values);
        weatherDetailsList.setAdapter(adapter);
	}
	
	Button load_img;
	  ImageView img;
	  Bitmap bitmap;
	  ProgressDialog pDialog;
	

}
