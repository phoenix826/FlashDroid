/*
4 * Settings Activity
 */

package com.usability.flashdroid;

import com.usability.flashdroid.model.Settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingsActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
    	final View.OnClickListener arrowHandler = new View.OnClickListener() {
			
			public void onClick(View v) {
				arrowAction(v.getId());
			}
		};
				
		final ImageButton upArrow01 = (ImageButton) findViewById(R.id.upButton01);
    	final ImageButton upArrow02 = (ImageButton) findViewById(R.id.upButton02);
		final ImageButton downArrow01 = (ImageButton) findViewById(R.id.upButton01);
    	final ImageButton downArrow02 = (ImageButton) findViewById(R.id.upButton02);
    	
    	upArrow01.setOnClickListener(arrowHandler);
    	downArrow01.setOnClickListener(arrowHandler);
    	upArrow02.setOnClickListener(arrowHandler);
    	downArrow02.setOnClickListener(arrowHandler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_settings, menu);
        return true;
    }
    
    private void arrowAction(int id) {
    	findViewById(id);
    	
    	switch(id) {
    	
	    	case R.id.upButton01: {
	    		TextView countdown = (TextView) findViewById(R.id.countdown);
	    		int newVal = (Integer.parseInt((String) countdown.getText()) + 5) % 60;
	    		setTimerValueByID(R.id.countdown, newVal);
	    		break;
	    	}
	    	
	    	case R.id.upButton02: {
	    		TextView countdown = (TextView) findViewById(R.id.countdown2);
	    		int newVal = (Integer.parseInt((String) countdown.getText()) + 5) % 1000;
	    		setTimerValueByID(R.id.countdown2, newVal);
	    		break;
	    	}
	    	
	    	case R.id.downButton01: {
	    		TextView countdown = (TextView) findViewById(R.id.countdown);
	    		int newVal = (Integer.parseInt((String) countdown.getText()) - 5) % 60;
	    		setTimerValueByID(R.id.countdown, newVal);
	    		break;
	    	}
	    	
	    	case R.id.downButton02: {
	    		TextView countdown = (TextView) findViewById(R.id.countdown2);
	    		int newVal = (Integer.parseInt((String) countdown.getText()) - 5) % 1000;
	    		setTimerValueByID(R.id.countdown2, newVal);
	    		break;
	    	}
    	}
    }
    
    private void setTimerValueByID(int id, int val) {
    	final TextView countdown = (TextView) findViewById(id);
		countdown.setText((new Integer(val)).toString());
		if(id == R.id.countdown) {
			Settings.setflipCardDuration(val * 1000);
		} else if (id == R.id.countdown2) {
			Settings.setStudySessionDuration(val * 1000);
		}
    }
}