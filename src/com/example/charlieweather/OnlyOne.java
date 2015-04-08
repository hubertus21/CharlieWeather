package com.example.charlieweather;

import com.example.charlieweather.data.ForecastForOneDay;
import com.example.charlieweather.data.Helper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OnlyOne extends Activity {

	private ForecastForOneDay forecast;
	private TextView temperatureView;
	private TextView weatherDescription;
	private ListView weatherDetailsList;
	private ImageView img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		forecast = City.newActivityForecast;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_only_one);
		setup();
		setDetails(forecast);
	}
	
	private void setup() {
		temperatureView = (TextView)this.findViewById(R.id.temperatureViewOnly);
		weatherDescription = (TextView)findViewById(R.id.weatherDetailsListOnly);
		weatherDetailsList = (ListView)findViewById(R.id.weatherDescriptionOnly);
		img = (ImageView)findViewById(R.id.weatherImageViewOnly);
		forecast = City.newActivityForecast;
		
		String temperature = forecast.getTemperature().toString();
		temperatureView.setText(temperature);
		
		String imgName = "i"+forecast.getImageUrl();
        img.setImageResource(getResources().getIdentifier(imgName, "drawable", Helper.context.getPackageName()));
		setDetails(forecast);
		weatherDescription.setText(forecast.getDescription().toString());		
	}
	
	
	private void setDetails(ForecastForOneDay _forecast) {
		String[] values = new String[5];
		values[0] = "Deszcz: "+Double.toString(_forecast.getRain())+" mm";
		values[0] = "Œnieg: "+Double.toString(_forecast.getSnow())+" mm";
		values[2] = "Wiatr: "+Double.toString(_forecast.getSpeed()) +" km/h";
		values[3] = "Wilgotnoœæ: "+Integer.toString(_forecast.getHumidity())+" %";
		values[4] = "Æiœnienie : "+Double.toString(_forecast.getPressure())+" hPa";
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values);
        weatherDetailsList.setAdapter(adapter);
	}
}
