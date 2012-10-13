package com.usability.flashdroid;

import com.usability.flashdroid.data_source.DeckDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class AddDeckActivity extends Activity {
	
	private DeckDataSource deckSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deck);
        // getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_deck, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void saveDeck(View view) {
        deckSource = new DeckDataSource(this);
        deckSource.open();
        
		EditText deckNameText = (EditText) findViewById(R.id.deckNameText);
		String deckName = deckNameText.getText().toString();
		deckSource.createDeck(deckName);
		
		deckSource.close();
		
		Context context = getApplicationContext();
		CharSequence text = "Successfully created deck";
		int duration = Toast.LENGTH_LONG;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		
		Intent newIntent = new Intent(AddDeckActivity.this, ManagerActivity.class);
		AddDeckActivity.this.startActivity(newIntent);
    }

}
