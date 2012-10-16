package com.usability.flashdroid;

import com.usability.flashdroid.data_source.DeckDataSource;
import com.usability.flashdroid.model.Deck;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditDeckActivity extends Activity {
	
	private DeckDataSource deckSource;
	private Deck currentDeck;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deck);
        
        this.deckSource = new DeckDataSource(this);
        
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("deckId");
        
        deckSource.open();
        this.currentDeck = deckSource.getDeckById(id);
        deckSource.close();
        
        EditText nameText = (EditText) findViewById(R.id.deckNameText);
        nameText.setText(this.currentDeck.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_edit_deck, menu);
        return true;
    }
    
    public void saveDeck(View view) {
        deckSource = new DeckDataSource(this);
        
		EditText deckNameText = (EditText) findViewById(R.id.deckNameText);
		String deckName = deckNameText.getText().toString();
		
        deckSource.open();
		deckSource.updateDeck(currentDeck.getId(), deckName);
		deckSource.close();
		
		Context context = getApplicationContext();
		CharSequence text = "Successfully updated deck";
		int duration = Toast.LENGTH_LONG;
		
		Intent newIntent = new Intent(this, ManagerActivity.class);
		EditDeckActivity.this.startActivity(newIntent);
		
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
    }
}
