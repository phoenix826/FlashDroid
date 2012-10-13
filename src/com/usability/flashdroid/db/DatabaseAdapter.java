package com.usability.flashdroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {
	
	public static final String DATABASE_NAME = "flashdroid";
	public static final int DATABASE_VERSION = 1;
	
	public static final String DECK_TABLE_NAME  = "decks";
	public static final String DECK_ID_COLUMN   = "id";
	public static final String DECK_NAME_COLUMN = "name";

	public static final String CARD_TABLE_NAME        = "cards";
	public static final String CARD_ID_COLUMN         = "id";
	public static final String CARD_DECK_ID_COLUMN    = "deck_id";
	public static final String CARD_TERM_COLUMN       = "term";
	public static final String CARD_DEFINITION_COLUMN = "definition";
	
	private static final String CREATE_DECK_TABLE_SQL = "create table if not exists "
		+ DECK_TABLE_NAME + " (id integer primary key autoincrement, name VARCHAR);";
	
	private static final String CREATE_CARD_TABLE_SQL = "create table if not exists "
		+ CARD_TABLE_NAME + " (id integer primary key autoincrement, deck_id integer, "
		+ "term VARCHAR, definition VARCHAR);";
	
	private Context context;
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	
	public DatabaseAdapter(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		
		public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_DECK_TABLE_SQL);
			db.execSQL(CREATE_CARD_TABLE_SQL);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}
		
	}
	
	public DatabaseAdapter open() {
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		db.close();
	}
	
	public long insertDeck(String name) {
		ContentValues values = new ContentValues();
		values.put(DECK_NAME_COLUMN, name);
		
		return db.insert(DECK_TABLE_NAME, null, values);
	}
	
	public boolean deleteDeck(long id) {
		String whereClause = DECK_ID_COLUMN + "=" + id;
		
		return db.delete(DECK_TABLE_NAME, whereClause, null) > 0;
	}
	
	public boolean updateDeck(long id, String name) {
		ContentValues values = new ContentValues();
		String whereClause = DECK_ID_COLUMN + "=" + id;
		values.put(DECK_NAME_COLUMN, name);
		
		return db.update(DECK_TABLE_NAME, values, whereClause, null) > 0;
	}
	
	public Cursor getAllDecks() {
		String[] columns = getDeckColumns();
		Cursor records = db.query(DECK_TABLE_NAME, columns, null, null, null, null, null);
		
		return records;
	}
	
	public Cursor getDeckById(long id) {
		String[] columns = getDeckColumns();
		String whereClause = DECK_ID_COLUMN + "=" + id;
		
		Cursor cursor = db.query(true, DECK_TABLE_NAME, columns, whereClause, null, null, null, null, null);
		
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		return cursor;
	}
	
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
}
