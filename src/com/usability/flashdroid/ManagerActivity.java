package com.usability.flashdroid;

import java.util.ArrayList;

import com.usability.flashdroid.data_source.DeckDataSource;
import com.usability.flashdroid.model.Deck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ManagerActivity extends Activity {
	
	private DeckDataSource deckSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        
        deckSource = new DeckDataSource(this);
        deckSource.open();
        
        ListView deckList = (ListView) findViewById(R.id.deckList);
        ArrayList<Deck> decks = deckSource.getAllDecks();
        
        deckList.setAdapter(new ArrayAdapter<Deck>(this,
        	android.R.layout.simple_list_item_1, decks));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manager, menu);
        return true;
    }
    
    public void addDeck(View view) {
		Intent newIntent = new Intent(ManagerActivity.this, AddDeckActivity.class);
		ManagerActivity.this.startActivity(newIntent);
    }
}