package com.usability.flashdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void startStudy(View view) {
    	Intent intent = new Intent(this, StudyActivity.class);
    }
    
    public void startManager(View view) {
    	Intent intent = new Intent(this, ManagerActivity.class);
    }
    
    public void startSettings(View view) {
    	
    }
    
    public void startStats(View view) {
    	
    }
}
