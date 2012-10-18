package com.usability.flashdroid;

import com.usability.flashdroid.data_source.DeckDataSource;
import com.usability.flashdroid.db.DatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class SelectDeckToStudyActivity extends Activity {

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
        setContentView(R.layout.activity_select_deck_to_study);

        this.deckSource = new DeckDataSource(this);
        this.deckList = (ListView) findViewById(R.id.selectDeckList);
        
        setListViewAdapter();
        registerForContextMenu(deckList);
        
        deckList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(SelectDeckToStudyActivity.this, StudyActivity.class);
				intent.putExtra("deckId", id);
				startActivity(intent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_select_deck_to_study, menu);
        return true;
    }
    
	private void setListViewAdapter() {
        String[] columns = new String[] { DatabaseHelper.DECK_NAME_COLUMN };
        int[] to = new int[] { R.id.deckName };
        
        deckSource.open();
        this.adapter = new SimpleCursorAdapter(this,
        		R.layout.deck_list_entry, deckSource.getAllDecksCursor(),
            	columns, to, 1);        
        deckList.setAdapter(this.adapter);
        deckSource.close();
    }
}
