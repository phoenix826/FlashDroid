package com.usability.flashdroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.usability.flashdroid.data_source.CardDataSource;
import com.usability.flashdroid.data_source.DeckDataSource;
import com.usability.flashdroid.db.DatabaseHelper;
import com.usability.flashdroid.model.Card;
import com.usability.flashdroid.model.Deck;

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
        cardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(ManageCardsActivity.this, EditCardActivity.class);
				
				
				intent.putExtra("deckId", currentDeck.getId());
				intent.putExtra("cardId", id);
				ManageCardsActivity.this.startActivity(intent);
			}
		});
        
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("deckId");
        
        deckSource.open();
        this.currentDeck = deckSource.getDeckById(id);
        deckSource.close();
        
        setListViewAdapter();
        registerForContextMenu(cardList);
        
        TextView textView = (TextView) findViewById(R.id.cardsHeader);
        textView.setText(this.currentDeck.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manage_cards, menu);
        return true;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
    	if (view.getId() == R.id.cardList) {
    		menu.add(Menu.NONE, 1, 1, "Edit");
    		menu.add(Menu.NONE, 2, 2, "Delete");
    	}
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	int position = item.getItemId();
    	AdapterContextMenuInfo itemInfo = (AdapterContextMenuInfo) item.getMenuInfo();
    	
    	cardSource.open();
    	final Card selectedCard = cardSource.getCardById(itemInfo.id);
    	cardSource.close();
    	
    	switch (position) {
    		case 1:
    	    	Intent newIntent = new Intent(this, EditCardActivity.class);
    	    	newIntent.putExtra("cardId", selectedCard.getId());
    	    	newIntent.putExtra("deckId", currentDeck.getId());
    	    	startActivity(newIntent);
    			
            	break;
            	
    		case 2:
    	    	AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    	    	
    	    	dialog.setTitle("Are you sure?");
    	    	dialog.setMessage("Are you sure you wish to delete card '" + selectedCard.getTerm() + "'?");
    	    	
    	    	dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog, int id) {
    					dialog.cancel();
    				}
    			});
    	    	
    	    	dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog, int id) {
    					cardSource.open();
    					cardSource.deleteCard(selectedCard);
    					cardSource.close();
    					
    					dialog.cancel();
    					
    	            	Toast toast = Toast.makeText(getApplicationContext(), "Card deleted", Toast.LENGTH_LONG);
    	            	toast.show();
    	            	
    	            	// Refresh the list by setting the adapter again
    	            	setListViewAdapter();
    				}
    			});
    	    	
    	    	AlertDialog alertDialog = dialog.create();
    	    	alertDialog.show();
    	    	
    	    	break;
    	}

    	
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
