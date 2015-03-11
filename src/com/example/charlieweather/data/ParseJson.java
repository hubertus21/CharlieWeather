package com.example.charlieweather.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJson {

	public static CityInfo getListOfForecastFromString(String jsonData) {
		CityInfo city = getCityInfo(jsonData);
		if(city==null)
			return null;
		List<ForecastForOneDay> list = new ArrayList<ForecastForOneDay>();
		try {
			JSONObject response = new JSONObject(jsonData);
			JSONArray recentForecast = response.getJSONArray("list");
			for (int i = 0; i < recentForecast.length(); i++)
				list.add(ParseJson.getForecast(recentForecast, i));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return city;
	}

	private static ForecastForOneDay getForecast(JSONArray recentForecast, int i) {
		ForecastForOneDay forecast = new ForecastForOneDay();
		try {
			JSONObject object = recentForecast.getJSONObject(i);
			if (object.has("dt"))
				forecast.setDateUnix(object.getString("dt"));
			if (object.has("temp"))
				forecast.setTemperature(getTemp(object.getJSONObject("temp")));
			if (object.has("pressure"))
				forecast.setPressure(object.getDouble("pressure"));
			if (object.has("humidity"))
				forecast.setHumidity(object.getInt("humidity"));
			if (object.has("speed"))
				forecast.setSpeed(object.getDouble("speed"));
			if (object.has("deg"))
				forecast.setDeg(object.getDouble("deg"));
			if (object.has("clouds"))
				forecast.setClouds(object.getInt("clouds"));
			if (object.has("snow"))
				forecast.setSnow(object.getDouble("snow"));
			if (object.has("rain"))
				forecast.setRain(object.getDouble("rain"));
			if (object.has("weather")) {
				JSONArray array = object.getJSONArray("weather");
				if (array.length() > 0) {
					JSONObject objectWeather = array.getJSONObject(0);
					if (objectWeather.has("main"))
						forecast.setMainWheater(objectWeather.getString("main"));
					if (objectWeather.has("description"))
						forecast.setDescription(objectWeather
								.getString("description"));
					if (objectWeather.has("icon"))
						forecast.setImageUrl(objectWeather.getString("icon"));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forecast;
	}

	private static Temperature getTemp(JSONObject tempObject) {
		Temperature temp = new Temperature();
		try {
			if (tempObject.has("day"))
				temp.setDay(tempObject.getDouble("day"));
			if (tempObject.has("min"))
				temp.setMin(tempObject.getDouble("min"));
			if (tempObject.has("max"))
				temp.setMax(tempObject.getDouble("max"));
			if (tempObject.has("night"))
				temp.setNight(tempObject.getDouble("night"));
			if (tempObject.has("eve"))
				temp.setEve(tempObject.getDouble("eve"));
			if (tempObject.has("morn"))
				temp.setMorning(tempObject.getDouble("morn"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return temp;
	}

	private static CityInfo getCityInfo(String jsonData) {
		CityInfo city = new CityInfo();
		try {
			JSONObject response = new JSONObject(jsonData);
			if(response.has("cod"))
				if(response.getString("cod")=="404")
					return null;
			if (response.has("city")) {
				JSONObject cityInfo = response.getJSONObject("city");
				if (cityInfo.has("name"))
					city.setName(cityInfo.getString("name"));
				if (cityInfo.has("coord")) {
					JSONObject object = cityInfo.getJSONObject("coord");
					if (object.has("lon") && object.has("lat"))
						city.setCoords(object.getString("lat"),
								object.getString("lon"));
				}
				if (response.has("country"))
					city.setCountry(response.getString("country"));
				if (response.has("list")) {
					JSONArray array = response.getJSONArray("list");
					for (int i = 0; i < array.length(); i++)
						city.addForecastForOneDay(getForecast(array, i));
				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return city;
	}
}
