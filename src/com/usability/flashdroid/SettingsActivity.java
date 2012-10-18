/*
4 * Settings Activity
 */

package com.usability.flashdroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
    	final View.OnClickListener upHandler = new View.OnClickListener() {
			
			public void onClick(View v) {
				upArrow(v.getId());
			}
		};
		
		final View.OnClickListener downHandler = new View.OnClickListener() {
			
			public void onClick(View v) {
				downArrow(v.getId());
			}
		};
		
		final ImageButton upArrow01 = (ImageButton) findViewById(R.id.upButton01);
    	final ImageButton upArrow02 = (ImageButton) findViewById(R.id.upButton02);
		final ImageButton downArrow01 = (ImageButton) findViewById(R.id.upButton01);
    	final ImageButton downArrow02 = (ImageButton) findViewById(R.id.upButton02);
    	
    	upArrow01.setOnClickListener(upHandler);
    	downArrow01.setOnClickListener(downHandler);
    	upArrow02.setOnClickListener(upHandler);
    	downArrow02.setOnClickListener(downHandler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_settings, menu);
        return true;
    }
    
    private void upArrow(int id) {
    	findViewById(id);
    }
    
    private void downArrow(int id) {
    	findViewById(id);
    }
}
