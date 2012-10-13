package com.usability.flashdroid;

import java.util.ArrayList;

import com.usability.flashdroid.data_source.DeckDataSource;
import com.usability.flashdroid.model.Deck;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class ManagerActivity extends ListActivity {
	
	private DeckDataSource deckSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        
        deckSource = new DeckDataSource(this);
        deckSource.open();
        
        ArrayList<Deck> decks = deckSource.getAllDecks();
        
        setListAdapter(new ArrayAdapter<Deck>(this,
        	android.R.layout.simple_list_item_1, decks));
        
        Button addDeck = (Button) findViewById(R.id.addDeck);
        
        addDeck.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newIntent = new Intent(ManagerActivity.this, AddDeckActivity.class);
				ManagerActivity.this.startActivity(newIntent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manager, menu);
        return true;
    }
}