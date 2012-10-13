package com.usability.flashdroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "flashdroid";
	public static final int DATABASE_VERSION = 1;
	
	public static final String DECK_TABLE_NAME  = "decks";
	public static final String DECK_ID_COLUMN   = "_id";
	public static final String DECK_NAME_COLUMN = "name";

	public static final String CARD_TABLE_NAME        = "cards";
	public static final String CARD_ID_COLUMN         = "_id";
	public static final String CARD_DECK_ID_COLUMN    = "deck_id";
	public static final String CARD_TERM_COLUMN       = "term";
	public static final String CARD_DEFINITION_COLUMN = "definition";
	
	private static final String CREATE_DECK_TABLE_SQL = "create table if not exists "
		+ DECK_TABLE_NAME + " (id integer primary key autoincrement, name VARCHAR);";
	
	private static final String CREATE_CARD_TABLE_SQL = "create table if not exists "
		+ CARD_TABLE_NAME + " (id integer primary key autoincrement, deck_id integer, "
		+ "term VARCHAR, definition VARCHAR);";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_DECK_TABLE_SQL);
		db.execSQL(CREATE_CARD_TABLE_SQL);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	/*
	
	public long insertCard(long deckId, String term, String definition) {
		ContentValues values = new ContentValues();
		values.put(CARD_DECK_ID_COLUMN, deckId);
		values.put(CARD_TERM_COLUMN, term);
		values.put(CARD_DEFINITION_COLUMN, definition);
		
		return db.insert(CARD_TABLE_NAME, null, values);
	}
	
	public boolean deleteCard(long id) {
		String whereClause = CARD_ID_COLUMN + "=" + id;
		
		return db.delete(CARD_TABLE_NAME, whereClause, null) > 0;
	}
	
	public boolean updateCard(long id, String term, String definition) {
		ContentValues values = new ContentValues();
		String whereClause = CARD_ID_COLUMN + "=" + id;
		values.put(CARD_TERM_COLUMN, term);
		values.put(CARD_DEFINITION_COLUMN, definition);
		
		return db.update(DECK_TABLE_NAME, values, whereClause, null) > 0;
	}
	
	public Cursor getAllCardsByDeckId(long deckId) {
		String[] columns = getCardColumns();
		String whereClause = CARD_DECK_ID_COLUMN + "=" + deckId;
		Cursor records = db.query(CARD_TABLE_NAME, columns, whereClause, null, null, null, null);
		
		return records;
	}
	
	public Cursor getCardById(long id) {
		String[] columns = getCardColumns();
		String whereClause = CARD_ID_COLUMN + "=" + id;
		
		Cursor cursor = db.query(true, CARD_TABLE_NAME, columns, whereClause, null, null, null, null, null);
		
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		return cursor;
	}
	
	private String[] getDeckColumns() {
		return new String[] { DECK_ID_COLUMN, DECK_NAME_COLUMN };
	}
	
	private String[] getCardColumns() {
		return new String[] { CARD_ID_COLUMN, CARD_DECK_ID_COLUMN, CARD_TERM_COLUMN, CARD_DEFINITION_COLUMN };
	}
	
	*/
}
