package com.usability.flashdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.usability.flashdroid.model.Log;
import com.usability.flashdroid.model.Stat;

public class StatisticsBreakdownActivity extends Activity {

	private Stat currentStat;
	private boolean fromStudySession = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_breakdown);
        
        Bundle extras = getIntent().getExtras();
        final int currentStatId = extras.getInt("statId");
        
        // If you came from a study session, the back button
        // works differently.
        if (extras.containsKey("studySession")) {
        	fromStudySession = true;
        }
        
        currentStat = Log.getAllStats().get(currentStatId);
        
        final TextView deckNameValue = (TextView) findViewById(R.id.deckValue);
        deckNameValue.setText(currentStat.getDeckName());
    	
    	final TextView totalTimeValue = (TextView) findViewById(R.id.totalTimeValue);
    	
    	// Custom util method for converting a millisecond value to a XX:XX "clock time format"
    	final String formattedTimeText = Util.convertMillisecondsToTimeString(currentStat.getTimeTaken());
    	totalTimeValue.setText(formattedTimeText);
    	
    	final TextView cardValue = (TextView) findViewById(R.id.cardStatValue);
    	cardValue.setText("" + currentStat.getNumCardsCompleted());
    	
    	final TextView reflipValue = (TextView) findViewById(R.id.reflipStatArea);
    	reflipValue.setText("" + currentStat.getNumReFlips());
    	
    	final Button statsDone = (Button) findViewById(R.id.statsDone);
    	statsDone.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				exitStats();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_statistics_breakdown, menu);
        return true;
    }
    
    @Override
    public void onBackPressed() {
    	exitStats();
    }
    
    private void exitStats() {
    	Intent newIntent;
    	if (fromStudySession) {
    		newIntent = new Intent(this, MainActivity.class);
    	}
    	else {
    		newIntent = new Intent(this, StatsActivity.class);
    	}
    	
    	startActivity(newIntent);
    }
}
