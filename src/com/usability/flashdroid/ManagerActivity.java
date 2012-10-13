package com.usability.flashdroid;

import java.util.ArrayList;

import com.usability.flashdroid.data_source.DeckDataSource;
import com.usability.flashdroid.model.Deck;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manager, menu);
        return true;
    }
}