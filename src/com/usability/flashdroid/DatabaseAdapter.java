package com.usability.flashdroid;

import android.content.Context;
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
	public static final String CARD_TABLE_ID_COLUMN   = "deck_id";
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
}
