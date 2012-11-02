/*
4 * Settings Activity
 */

package com.usability.flashdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.usability.flashdroid.model.Settings;

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
				
		final TextView countdown2 = (TextView) findViewById(R.id.countdown2);
		countdown2.setText(Util.convertMillisecondsToTimeString(Settings.getStudySessionDuration()));
		
    	final ImageButton upArrow02 = (ImageButton) findViewById(R.id.upButton02);
    	final ImageButton downArrow02 = (ImageButton) findViewById(R.id.downButton02);
    	
    	final Button done = (Button) findViewById(R.id.settingsDone);
    	done.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent newIntent = new Intent(SettingsActivity.this, MainActivity.class);
				startActivity(newIntent);
			}
		});
    	
    	final CheckBox colorCheckbox = (CheckBox) findViewById(R.id.checkBox1);
    	colorCheckbox.setChecked(Settings.isAlternatingCardColors());
    	colorCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Settings.setAlternatingCardColors(isChecked);
			}
		});
    	
    	final TextView randomColorLabel = (TextView) findViewById(R.id.randomColorLabel);
    	randomColorLabel.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				boolean checkedCurrently = colorCheckbox.isChecked();
				colorCheckbox.setChecked(!checkedCurrently);
				Settings.setAlternatingCardColors(!checkedCurrently);
			}
		});
    	
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
	    	case R.id.upButton02: {
	    		int newVal = (int) (((Settings.getStudySessionDuration() / 1000) + 5) % 1000);
	    		setTimerValueByID(R.id.countdown2, newVal);
	    		break;
	    	}
	    	
	    	case R.id.downButton02: {
	    		if ((Settings.getStudySessionDuration() / 1000) - 5 >= 5) {
	    			int newVal = (int) (((Settings.getStudySessionDuration() / 1000) - 5) % 1000);
	    			setTimerValueByID(R.id.countdown2, newVal);
	    		}
	    		break;
	    	}
    	}
    }
    
    private void setTimerValueByID(int id, int val) {
    	final TextView countdown = (TextView) findViewById(id);
		countdown.setText(Util.convertMillisecondsToTimeString((long)(val * 1000)));
		if (id == R.id.countdown2) {
			Settings.setStudySessionDuration(val * 1000);
		}
    }
}