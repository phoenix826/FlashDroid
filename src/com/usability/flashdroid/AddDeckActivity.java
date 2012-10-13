package com.usability.flashdroid;

import com.usability.flashdroid.data_source.DeckDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class AddDeckActivity extends Activity {
	
	private DeckDataSource deckSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deck);
        // getActionBar().setDisplayHomeAsUpEnabled(true);
        
        deckSource = new DeckDataSource(this);
        
        Button saveButton = (Button) findViewById(R.id.saveDeck);
        
        saveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		        deckSource.open();
		        
				EditText deckNameText = (EditText) findViewById(R.id.deckNameText);
				String deckName = deckNameText.getText().toString();
				deckSource.createDeck(deckName);
				
				deckSource.close();
				
				Intent newIntent = new Intent(AddDeckActivity.this, ManagerActivity.class);
				AddDeckActivity.this.startActivity(newIntent);
			}
		});
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

}
