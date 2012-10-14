package com.usability.flashdroid;

import com.usability.flashdroid.data_source.DeckDataSource;
import com.usability.flashdroid.db.DatabaseHelper;
import com.usability.flashdroid.model.Deck;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ManagerActivity extends Activity {
	
	/**
	 * The Deck Data Source object used to interact with Decks in the database.
	 */
	private DeckDataSource deckSource;
	
	/**
	 * The ListView that the decks will reside in.
	 */
	private ListView deckList;
	
	/**
	 * The ListView adapter.
	 */
	private BaseAdapter adapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        
        this.deckSource = new DeckDataSource(this);
        
        this.deckList = (ListView) findViewById(R.id.deckList);
        
        setListViewAdapter();
        registerForContextMenu(deckList);
        
        deckList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Intent intent = new Intent(ManagerActivity.this, ManageCards.class);
				//ManagerActivity.this.startActivity(intent);
          	
				deckSource.open();
				Deck d = deckSource.getDeckById(id);
				deckSource.close();
				
            	CharSequence text = "You clicked Deck id: " + d.getId() + ", name: " + d.getName();
            	int duration = Toast.LENGTH_LONG;

            	Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            	toast.show();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.activity_manager, menu);
        return true;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
    	if (view.getId() == R.id.deckList) {
    		menu.add(Menu.NONE, 0, 0, "Remove deck");
    	}
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo itemInfo = (AdapterContextMenuInfo) item.getMenuInfo();
    	deckSource.open();
    	final Deck selectedDeck = deckSource.getDeckById(itemInfo.id);
    	deckSource.close();
    	
    	AlertDialog.Builder dialog = new AlertDialog.Builder(ManagerActivity.this);
    	
    	dialog.setTitle("Are you sure?");
    	dialog.setMessage("Are you sure you wish to delete deck '" + selectedDeck.getName() + "'?");
    	
    	dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
    	
    	dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				deckSource.open();
				deckSource.deleteDeck(selectedDeck);
				deckSource.close();
				
				dialog.cancel();
				
            	Toast toast = Toast.makeText(getApplicationContext(), "Deck deleted", Toast.LENGTH_LONG);
            	toast.show();
            	
            	// Notifying the adapter that the data set has changed doesn't refresh the ListView
            	setListViewAdapter();
			}
		});
    	
    	AlertDialog alertDialog = dialog.create();
    	alertDialog.show();
    	
    	return true;
    }
    
    public void addDeck(View view) {
		Intent newIntent = new Intent(ManagerActivity.this, AddDeckActivity.class);
		ManagerActivity.this.startActivity(newIntent);
    }
    
	private void setListViewAdapter() {
        String[] columns = new String[] { DatabaseHelper.DECK_NAME_COLUMN };
        int[] to = new int[] { R.id.deckName };
        
        deckSource.open();
        this.adapter = new SimpleCursorAdapter(ManagerActivity.this,
        		R.layout.deck_list_entry, deckSource.getAllDecksCursor(),
            	columns, to, 1);        
        deckList.setAdapter(this.adapter);
        deckSource.close();
    }
}