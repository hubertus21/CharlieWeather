package com.example.charlieweather;

import java.io.InputStream;
import java.net.URL;

import com.example.charlieweather.City.LoadImage;
import com.example.charlieweather.data.ForecastForOneDay;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class CityWeather extends Activity {
	
	private ForecastForOneDay forecast;
	private TextView temperatureView;
	private TextView weatherDescription;
	private ListView weatherDetailsList;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_city_weather, container,false);
				setup();
				return rootView;
	    }

	private void setup() {
		temperatureView = (TextView)this.findViewById(R.id.temperatureViewOne);
		weatherDescription = (TextView)findViewById(R.id.weatherDescriptionViewOne);
		weatherDetailsList = (ListView)findViewById(R.id.weatherDetailsListOne);
		img = (ImageView)rootView.findViewById(R.id.weatherDescriptionViewOne);
		forecast = City.newActivityForecast;
		
		String temperature = forecast.getTemperature().toString();
		temperatureView.setText(temperature);
		
		setDetails(forecast);
		weatherDescription.setText(forecast.getDescription().toString());
		
		new LoadImage().execute(forecast.getImageUrl());
		
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
	
	//Class LoadImage
	
	private class LoadImage extends AsyncTask<String, String, Bitmap> {
	    @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(getActivity());
	            pDialog.setMessage("Loading Image ....");
	            pDialog.show();
	    }
	       protected Bitmap doInBackground(String... args) {
	         try {
	               bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
	        } catch (Exception e) {
	              e.printStackTrace();
	        }
	      return bitmap;
	       }
	       protected void onPostExecute(Bitmap image) {
	         if(image != null){
	           img.setImageBitmap(image);
	           pDialog.dismiss();
	         }else{
	           pDialog.dismiss();
	           Toast.makeText(getActivity(), "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
	         }
	       }
	   }
	Button load_img;
	  ImageView img;
	  Bitmap bitmap;
	  ProgressDialog pDialog;
	

}
