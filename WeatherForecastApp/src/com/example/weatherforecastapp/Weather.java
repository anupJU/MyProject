package com.example.weatherforecastapp;

public class Weather {
	public static final String TAG_WEATHER = "weather";
	public static final String TAG_MAIN = "main";
	public static final String TAG_DESCRIPTION = "description";
	public static final String TAG_ICON = "icon";
	
	private String mMain;
	private String mDescription;
	private String mIcon;
	
	public void setMain(String main) {
		this.mMain = main;
	}
	public void setDescription(String description) {
		this.mDescription = description;
	}
	public void setIcon(String icon) {
		this.mIcon = icon;
	}
	public String getMain() {
		return mMain;
	}
	public String getDescription() {
		return mDescription;
	}
	public String getIcon() {
		return mIcon;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return mMain + "\n" + mDescription + "\n" + mIcon;
	}
	
	
	

}
