/**
 * 
 */
package com.usability.flashdroid.model;

import java.util.ArrayList;

/**
 * @author Edward Dinki
 *
 */
public class Log
{
	private static Log log;
	
	ArrayList<Stat> stats;

	/**
	 * 
	 */
	private Log()
	{
		stats = new ArrayList<Stat>();
	}
	
	public static Log getinstance()
	{
		if(log == null){
			log = new Log();
		}
		return log;
	}
	
	public void addStat(Stat s)
	{
		stats.add(s);
	}
	
	public Stat getStat(int id)
	{
		for (Stat i : stats)
		{
			if(i.getID() == id)
			{
				return i;
			}
		}
		return null;
	}

}
