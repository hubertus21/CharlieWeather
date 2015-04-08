package com.example.charlieweather;


import java.io.InputStream;
import java.net.URL;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charlieweather.data.ForecastForOneDay;
import com.example.charlieweather.data.Helper;
import com.example.charlieweather.CityWeather;
import com.example.charlieweather.TermometerView;

@SuppressLint("ValidFragment")
public class City extends Fragment {
	
	private List<Weather> nextDays;
	private String name;
	private View rootView;
	ListView nextDaysView;
	TextView weatherDetailsList;
	TextView weatherDescription;
	TextView dateView;
	ImageView imageImg;
	TermometerView termometerView;
	private List<ForecastForOneDay> forecast;
	public static ForecastForOneDay newActivityForecast;
	
	
	public City(){
	}
	
	public City(String _name, List<ForecastForOneDay> _forecast){
		name = _name;
		forecast = _forecast;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_city, container,false);
        // Inflate the layout for this fragment
		setup();
		setValues();
        return rootView;
    }

	private void setup() {
		
		nextDaysView = (ListView)rootView.findViewById(R.id.nextDaysListView);
		weatherDetailsList = (TextView)rootView.findViewById(R.id.weatherDetailsListOne);
		weatherDescription = (TextView)rootView.findViewById(R.id.weatherDescriptionOnly);
		imageImg = (ImageView)rootView.findViewById(R.id.weatherImageViewOnly);
		termometerView = (TermometerView)rootView.findViewById(R.id.termometerView1);
		dateView=(TextView)rootView.findViewById(R.id.Date);
		
	}
	
	public void setValues(){
		termometerView.setTemperature(forecast.get(0).getTemperature().getMax(),forecast.get(0).getTemperature().getMin());
		String[] values = new String[forecast.size()];
		String imgName = "i"+forecast.get(0).getImageUrl();
        imageImg.setImageResource(getResources().getIdentifier(imgName, "drawable", getActivity().getPackageName()));
        

        
		setDetails(forecast.get(0));
		weatherDescription.setText(forecast.get(0).getDescription().toString());
		
		
		for(int i=0;i<forecast.size();i++){
			values[i] = forecast.get(i).getDateString()+ "\t" + forecast.get(i).getTemperature().getDay();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_list_item_1, android.R.id.text1, values);
	    nextDaysView.setAdapter(adapter);
	    nextDaysView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
	        	newActivityForecast = forecast.get(position);
	        	Intent myIntent = new Intent(getActivity(), OnlyOne.class);
	        	//rootView.getContext().startActivity(myIntent);
	        	startActivity(myIntent);
	        }
	    });
	    
	}
	private void setDetails(ForecastForOneDay forecast) {
		dateView.setText(forecast.getDateString());
		String values = "";
		values += "Deszcz: "+Double.toString(forecast.getRain())+" mm\n";
		values += "Œnieg: "+Double.toString(forecast.getSnow())+" mm\n";
		values += "Wiatr: "+Double.toString(forecast.getSpeed()) +" km/h\n";
		values += "Wilgotnoœæ: "+Integer.toString(forecast.getHumidity())+" %\n";
		values += "Æiœnienie : "+Double.toString(forecast.getPressure())+" hPa\n";
		weatherDetailsList.setText(values);
	}
	
	
	
	Button load_img;
	  ImageView img;
	  Bitmap bitmap;
	  ProgressDialog pDialog;
	
}


