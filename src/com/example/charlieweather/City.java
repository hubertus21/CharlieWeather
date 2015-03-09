package com.example.charlieweather;


import java.util.List;

import com.example.charlieweather.data.ForecastForOneDay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class City extends Fragment {
	
	private List<Weather> nextDays;
	private String name;
	private View rootView;
	ListView nextDaysView;
	ListView weatherDetailsList;
	TextView weatherDescription;
	TextView temperatureView;
	public static ForecastForOneDay newActivityForecast;
	
	
	public City(){
		
	}
	public City(String _name){
		name = _name;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_city, container,false);
        // Inflate the layout for this fragment
		setup();
        return inflater.inflate(R.layout.fragment_city, container, false);
    }

	private void setup() {
		nextDaysView = (ListView)rootView.findViewById(R.id.nextDaysListView);
		weatherDetailsList = (ListView)rootView.findViewById(R.id.weatherDetailsListOne);
		weatherDescription = (TextView)rootView.findViewById(R.id.weatherDescriptionViewOne);
		
		temperatureView = (TextView)rootView.findViewById(R.id.temperatureViewOne);
	}
	
	public void setValues(final List<ForecastForOneDay> forecast){
		String temperature = forecast.get(0).getTemperature().toString();
		
		temperatureView.setText(temperature);
		String[] values = new String[forecast.size()];
		
		setDetails(forecast.get(0));
		weatherDescription.setText(forecast.get(0).getDescription().toString());
		
		
		
		for(int i=0;i<forecast.size();i++){
			values[i] = forecast.get(i).getDateString()+ "\t" + forecast.get(i).getTemperature().toString();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_list_item_1, android.R.id.text1, values);
	    nextDaysView.setAdapter(adapter);
	    nextDaysView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
	        	newActivityForecast = forecast.get(position);
	        	Intent myIntent = new Intent(getActivity(), CityWeather.class);
	        	getActivity().startActivity(myIntent);
	        }
	    });
	}
	private void setDetails(ForecastForOneDay forecast) {
		String[] values = new String[5];
		values[0] = "Deszcz: "+Double.toString(forecast.getRain())+" mm";
		values[0] = "Œnieg: "+Double.toString(forecast.getSnow())+" mm";
		values[2] = "Wiatr: "+Double.toString(forecast.getSpeed()) +" km/h";
		values[3] = "Wilgotnoœæ: "+Integer.toString(forecast.getHumidity())+" %";
		values[4] = "Æiœnienie : "+Double.toString(forecast.getPressure())+" hPa";
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_list_item_1, android.R.id.text1, values);
        weatherDetailsList.setAdapter(adapter);
	}
}
