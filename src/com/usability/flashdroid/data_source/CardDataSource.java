package com.usability.flashdroid.data_source;

import com.usability.flashdroid.db.DatabaseHelper;
import com.usability.flashdroid.model.Card;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class CardDataSource extends DataSource {
	
	private String[] columns = { DatabaseHelper.CARD_ID_COLUMN,
		DatabaseHelper.CARD_DECK_ID_COLUMN, DatabaseHelper.CARD_TERM_COLUMN,
		DatabaseHelper.CARD_DEFINITION_COLUMN };
	
	public CardDataSource(Context context) {
		super(context);
	}
	
	public Card createCard(long deckId, String term, String definition) {
		ContentValues values = new ContentValues();
		
		values.put(DatabaseHelper.CARD_DECK_ID_COLUMN, deckId);
		values.put(DatabaseHelper.CARD_TERM_COLUMN, term);
		values.put(DatabaseHelper.CARD_DEFINITION_COLUMN, definition);
		
		long newId = db.insert(DatabaseHelper.CARD_TABLE_NAME, null, values);

		String whereClause = DatabaseHelper.CARD_ID_COLUMN + "=" + newId;
		return fetchCard(whereClause);
	}
	
	public boolean updateCard(long id, String term, String definition) {
		ContentValues values = new ContentValues();
		String whereClause = DatabaseHelper.CARD_ID_COLUMN + "=" + id;
		values.put(DatabaseHelper.CARD_TERM_COLUMN, term);
		values.put(DatabaseHelper.CARD_DEFINITION_COLUMN, definition);
		
		return db.update(DatabaseHelper.CARD_TABLE_NAME, values, whereClause, null) > 0;
	}
	
	public Card getCardById(long id) {
		String whereClause = DatabaseHelper.CARD_ID_COLUMN + "=" + id;
		return fetchCard(whereClause);
	}
	
	public Cursor getCardsByDeckId(long deckId) {
		String whereClause = DatabaseHelper.CARD_DECK_ID_COLUMN + "=" + deckId;
		
		Cursor cursor = db.query(DatabaseHelper.CARD_TABLE_NAME, columns,
			whereClause, null, null, null, null);

		return cursor;
	}

	public boolean deleteCard(Card card) {
		long id = card.getId();
		String whereClause = DatabaseHelper.CARD_ID_COLUMN + "=" + id;

		return db.delete(DatabaseHelper.CARD_TABLE_NAME, whereClause, null) > 0;
	}
	
	private Card fetchCard(String whereClause) {
		Cursor cursor = db.query(DatabaseHelper.CARD_TABLE_NAME, columns,
				whereClause, null, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		long id = cursor.getLong(0);
		long deckId = cursor.getLong(1);
		String term = cursor.getString(2);
		String definition = cursor.getString(3);
		
		Card card = new Card(id, deckId, term, definition);
		cursor.close();

		return card;
	}

}
