package com.usability.flashdroid;

import java.util.concurrent.TimeUnit;

public class Util {
	public static String convertMillisecondsToTimeString(final long milli) {
		final long numMins = TimeUnit.MILLISECONDS.toMinutes(milli); 
		final long numSeconds = TimeUnit.MILLISECONDS.toSeconds(milli) - 
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milli));
		
		final String secondsString = (numSeconds < 10) ? "0" + numSeconds : "" + numSeconds;
		
		return String.format("%s:%s", 
			numMins,
			secondsString
		);
	}
}
