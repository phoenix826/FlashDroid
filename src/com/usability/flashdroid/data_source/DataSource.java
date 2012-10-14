package com.usability.flashdroid.data_source;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.usability.flashdroid.db.DatabaseHelper;

public class DataSource {
	
	protected SQLiteDatabase db;
	protected DatabaseHelper dbHelper;

	public DataSource(Context context) {
		this.dbHelper = new DatabaseHelper(context);
	}

	public void open() {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		db.close();
	}
}
