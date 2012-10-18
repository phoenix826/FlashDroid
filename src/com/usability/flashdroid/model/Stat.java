/**
 * 
 */
package com.usability.flashdroid.model;

import java.util.Date;

/**
 * @author Edward Dinki
 *
 *The Stat class holds statistics about the user's usage of the program. Each
 *Stat represents the statistics from a single study session.
 *
 */
public class Stat
{
	
	int id;
	String deckName = "";
	long timeTaken;
	int numCardsCompleted;
	int numReFlips;
	Date startDate;

	/**
	 * 
	 */
	public Stat(int id, String deckName, long tT, int numCC, int numRF, Date start)
	{
		this.id = id;
		this.deckName = deckName;
		this.timeTaken = tT;
		this.numCardsCompleted = numCC;
		this.numReFlips = numRF;
		this.startDate = start;
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getDeckName()
	{
		return deckName;
	}
	
	public long getTimeTaken()
	{
		return timeTaken;
	}
	
	public int getNumCardsCompleted()
	{
		return numCardsCompleted;
	}
	
	public int getNumReFlips()
	{
		return numReFlips;
	}
	
	public String toString()
	{
		String[] result = startDate.toString().split(" ");
		return deckName + " - " + result[0] + " " + result[1] + " " + result[2] + " " + result[3];
	}

}
