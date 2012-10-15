package com.usability.flashdroid;

import com.usability.flashdroid.data_source.CardDataSource;
import com.usability.flashdroid.data_source.DeckDataSource;
import com.usability.flashdroid.db.DatabaseHelper;
import com.usability.flashdroid.model.Deck;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;

public class ManageCardsActivity extends Activity {
	
	private Deck currentDeck;
	private DeckDataSource deckSource;
	private CardDataSource cardSource;
	private ListView cardList;
	private BaseAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cards);
        
        this.deckSource = new DeckDataSource(this);
        this.cardSource = new CardDataSource(this);
        
        this.cardList = (ListView) findViewById(R.id.cardList);
        
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("deckId");
        
        deckSource.open();
        this.currentDeck = deckSource.getDeckById(id);
        deckSource.close();
        
        setListViewAdapter();
        
        TextView textView = (TextView) findViewById(R.id.cardsHeader);
        textView.setText(this.currentDeck.getName());
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
    
    @Override
    public void onBackPressed() {
    	Intent newIntent = new Intent(this, ManagerActivity.class);
    	startActivity(newIntent);
    }
    
    public void addCard(View view) {
		Intent newIntent = new Intent(this, AddCardActivity.class);
		newIntent.putExtra("deckId", currentDeck.getId());
		startActivity(newIntent);
    }
    
	private void setListViewAdapter() {
        String[] columns = new String[] { DatabaseHelper.CARD_TERM_COLUMN };
        int[] to = new int[] { R.id.cardName };
        
        cardSource.open();
        this.adapter = new SimpleCursorAdapter(ManageCardsActivity.this,
        	R.layout.card_list_entry, cardSource.getCardsByDeckId(currentDeck.getId()),
            columns, to, 1);
        cardList.setAdapter(this.adapter);
        deckSource.close();
    }

}
