package com.usability.flashdroid.data_source;

import java.util.ArrayList;

import com.usability.flashdroid.db.DatabaseHelper;
import com.usability.flashdroid.model.Deck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DeckDataSource extends DataSource {

	private String[] columns = { DatabaseHelper.DECK_ID_COLUMN,
			DatabaseHelper.DECK_NAME_COLUMN };

	public DeckDataSource(Context context) {
		super(context);
	}

	public Deck createDeck(String name) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.DECK_NAME_COLUMN, name);
		long newId = db.insert(DatabaseHelper.DECK_TABLE_NAME, null, values);

		String whereClause = DatabaseHelper.DECK_ID_COLUMN + "=" + newId;
		return fetchDeck(whereClause);
	}

	public Deck getDeckById(long id) {
		String whereClause = DatabaseHelper.DECK_ID_COLUMN + "=" + id;
		return fetchDeck(whereClause);
	}

	public ArrayList<Deck> getAllDecks() {
		ArrayList<Deck> decks = new ArrayList<Deck>();
		Cursor cursor = db.query(DatabaseHelper.DECK_TABLE_NAME, columns, null,
				null, null, null, null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			Deck deck = new Deck(cursor.getLong(0), cursor.getString(1));
			decks.add(deck);
			cursor.moveToNext();
		}

		cursor.close();
		return decks;
	}
	
	public Cursor getAllDecksCursor() {
		Cursor cursor = db.query(DatabaseHelper.DECK_TABLE_NAME, columns, null,
				null, null, null, null);

		return cursor;
	}

	public boolean deleteDeck(Deck deck) {
		long id = deck.getId();
		String whereClause = DatabaseHelper.DECK_ID_COLUMN + "=" + id;

		return db.delete(DatabaseHelper.DECK_TABLE_NAME, whereClause, null) > 0;
	}

	private Deck fetchDeck(String whereClause) {
		Cursor cursor = db.query(DatabaseHelper.DECK_TABLE_NAME, columns,
				whereClause, null, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}

		Deck deck = new Deck(cursor.getLong(0), cursor.getString(1));
		cursor.close();

		return deck;
	}

}
