package com.usability.flashdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button manageButton = (Button) findViewById(R.id.manageButton);
        
        manageButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newIntent = new Intent(MainActivity.this, ManagerActivity.class);
				MainActivity.this.startActivity(newIntent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void startStudy(View view) {
    	Intent intent = new Intent(this, StudyActivity.class);
    	startActivity(intent);
    }
    
    public void startManager(View view) {
    	Intent intent = new Intent(this, ManagerActivity.class);
    	startActivity(intent);
    }
    
    public void startSettings(View view) {
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);
    }
    
    public void startStats(View view) {
    	Intent intent = new Intent(this, StatsActivity.class);
    	startActivity(intent);
    }
}
