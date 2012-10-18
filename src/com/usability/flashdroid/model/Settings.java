package com.usability.flashdroid.model;

public class Settings {
	private static Settings instance;
	
	// TODO: For Shaun, make this a drop down list or however you want to do it
	private static long studySessionDuration = 600000;
	
	private static boolean alternateCardColors = false;
	
	private Settings() {
	}
	
	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		
		return instance;
	}
	
	public static long getStudySessionDuration() {
		return studySessionDuration;
	}
	
	public static boolean isAlternatingCardColors() {
		return alternateCardColors;
	}
}
