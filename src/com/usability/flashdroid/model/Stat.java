/**
 * 
 */
package com.usability.flashdroid.model;

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

	/**
	 * 
	 */
	public Stat(int id, String deckName, long tT, int numCC, int numRF)
	{
		this.id = id;
		this.deckName = deckName;
		this.timeTaken = tT;
		this.numCardsCompleted = numCC;
		this.numReFlips = numRF;
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
		return deckName;
	}

}
