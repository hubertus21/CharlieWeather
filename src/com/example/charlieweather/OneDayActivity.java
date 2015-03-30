package com.example.charlieweather;

import com.example.charlieweather.data.ForecastForOneDay;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OneDayActivity extends Activity {
	
	private ForecastForOneDay forecast;
	ListView weatherDetailsList;
	TextView weatherDescription;
	TextView temperatureView;
	TermometerView termometerView;
	ImageView img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one_day_activity);
		setup();
	}

	private void setup() {
		forecast = City.newActivityForecast;
		weatherDetailsList = (ListView)findViewById(R.id.weatherDetailsList);
		weatherDescription = (TextView)findViewById(R.id.weatherDescription);
		img = (ImageView)findViewById(R.id.weatherImageView);
		termometerView = (TermometerView)findViewById(R.id.termometerView);
		
		temperatureView = (TextView)findViewById(R.id.temperatureView);
		setDetails(forecast);
	}
	
	private void setDetails(ForecastForOneDay forecast) {
		String[] values = new String[5];
		values[0] = "Deszcz: "+Double.toString(forecast.getRain())+" mm";
		values[1] = "Œnieg: "+Double.toString(forecast.getSnow())+" mm";
		values[2] = "Wiatr: "+Double.toString(forecast.getSpeed()) +" km/h";
		values[3] = "Wilgotnoœæ: "+Integer.toString(forecast.getHumidity())+" %";
		values[4] = "Æiœnienie : "+Double.toString(forecast.getPressure())+" hPa";
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, android.R.id.text1, values);
        
		if(weatherDetailsList==null){
			Toast.makeText(getApplicationContext(), "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
		}
		
		weatherDetailsList.setAdapter(adapter);
	}

}
