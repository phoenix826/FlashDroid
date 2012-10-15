package com.usability.flashdroid;

import com.usability.flashdroid.data_source.DeckDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EditDeckActivity extends Activity {
	private DeckDataSource deckSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deck);
        
        this.deckSource = new DeckDataSource(this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_edit_deck, menu);
        return true;
    }
}
