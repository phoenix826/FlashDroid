package com.usability.flashdroid.model;

public class Settings {
	private static Settings instance;
	
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
	
	public static void setStudySessionDuration(long dur) {
		studySessionDuration = dur;
	}
	
	public static boolean isAlternatingCardColors() {
		return alternateCardColors;
	}
	
	public static void setAlternatingCardColors(boolean alt) {
		alternateCardColors = alt;
	}
}