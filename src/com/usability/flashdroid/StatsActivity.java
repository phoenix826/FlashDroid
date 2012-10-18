package com.usability.flashdroid;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.usability.flashdroid.model.Log;
import com.usability.flashdroid.model.Stat;

public class StatsActivity extends Activity {
	
	/**
	 * The ListView that the stats will reside in.
	 */
	private ListView statsList;
	
	/**
	 * The instance of the Log so we can retreive the list of stats
	 */
	private Log log;
	
	/**
	 * Array of Stats
	 */
	private ArrayList<Stat> stats;
	
	/**
	 * The ListView adapter.
	 */
	private ArrayAdapter<Stat> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        
        this.statsList = (ListView) findViewById(R.id.statsList);
        this.log = Log.getinstance();
        
        stats = log.getAllStats();
        
        //For testing purposes, lets populate the array of Statistics
        //id, deckname, time taken, number cards completed, number of reflips
        stats.add(new Stat(1,"Dinki Deck", 1, 2, 3, new Date()));
        stats.add(new Stat(1,"Devos Deck", 10, 20, 30,  new Date()));
        stats.add(new Stat(1,"Lame DECK deck kced Deck", 11, 22, 33,  new Date()));
        
        this.adapter = new ArrayAdapter<Stat>(StatsActivity.this, android.R.layout.simple_list_item_1, stats);
        
        statsList.setAdapter(adapter);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_stats, menu);
        return true;
    }
}
