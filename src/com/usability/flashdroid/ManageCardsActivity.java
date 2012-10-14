package com.usability.flashdroid;

import com.usability.flashdroid.data_source.DeckDataSource;
import com.usability.flashdroid.model.Deck;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ManageCardsActivity extends Activity {
	
	private DeckDataSource deckSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cards);
        
        this.deckSource = new DeckDataSource(this);
        
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("deckId");
        
        deckSource.open();
        Deck deck = deckSource.getDeckById(id);
        deckSource.close();
        
        TextView textView = (TextView) findViewById(R.id.cardsHeader);
        textView.setText(deck.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manage_cards, menu);
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
