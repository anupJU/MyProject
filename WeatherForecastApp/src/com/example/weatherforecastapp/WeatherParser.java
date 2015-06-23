package com.example.weatherforecastapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.weathermap.WebServiceHandler;

public class WeatherParser {
	public static List<Weather> getWeathers(String url){
		List<Weather> weathers = null;
		
		try {
			String json = WebServiceHandler.getJSONFeed(url);
			JSONObject jsonData = new JSONObject(json);
			JSONArray weatherJsonArray = jsonData.getJSONArray(Weather.TAG_WEATHER);
			
			weathers = new ArrayList<Weather>();
			
			for (int i = 0; i < weatherJsonArray.length(); i++) {
				
			Weather weather = new Weather();
			JSONObject weatherObj = weatherJsonArray.getJSONObject(i);
			weather.setMain(weatherObj.getString(Weather.TAG_MAIN));
			weather.setDescription(weatherObj.getString(Weather.TAG_DESCRIPTION));
			weather.setIcon(weatherObj.getString(Weather.TAG_ICON));
			
			weathers.add(weather);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return weathers;
		
		
		
		
	}

}
