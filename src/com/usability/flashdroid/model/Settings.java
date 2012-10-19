package com.usability.flashdroid.model;

public class Settings {
	private static Settings instance;
	
	// TODO: For Shaun, make this a drop down list or however you want to do it
	private static long studySessionDuration = 600000;
	private static long flipCardDuration = 9000;
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
	
	public static long getflipCardDuration() {
		return flipCardDuration;
	}
	
	public static void setflipCardDuration(long dur) {
		flipCardDuration = dur;
	}
	
	public static boolean isAlternatingCardColors() {
		return alternateCardColors;
	}
	
	public static void setAlternatingCardColors(boolean alt) {
		alternateCardColors = alt;
	}
}