package com.example.charlieweather.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;

public class ParseJson {

	public static CityInfo getListOfForecastFromString(String jsonData){
		CityInfo city=getCityInfo(jsonData);
		List<ForecastForOneDay> list=new ArrayList<ForecastForOneDay>();
		try {
			JSONObject response=new JSONObject(jsonData);
			JSONArray recentForecast=response.getJSONArray("list");
			for(int i=0;i<recentForecast.length();i++)
					list.add(ParseJson.getForecast(recentForecast,i));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		return city;
	}

	private static ForecastForOneDay getForecast(JSONArray recentForecast, int i) {
		ForecastForOneDay forecast=new ForecastForOneDay();

		
		return forecast;
	}
	private static CityInfo getCityInfo(String jsonData){
		CityInfo city=new CityInfo();
		try {
			JSONObject response=new JSONObject(jsonData);
			if(response.has("city")){
				JSONObject cityInfo=response.getJSONObject("city");
				if(cityInfo.has("id"))
					city.setID(cityInfo.getInt("id"));
				if(cityInfo.has("name"))
					city.setName(cityInfo.getString("name"));
				if(cityInfo.has("coord"))
				{
					JSONObject object=cityInfo.getJSONObject("coord");
					if(object.has("lon") && object.has("lat"))
						city.setCoords(object.getString("lat"),object.getString("lon"));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return city;
	}
}
