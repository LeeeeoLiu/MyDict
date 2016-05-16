package com.leeeeo.mydict;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

	public DB(Context context) {
		super(context, "vocab", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE vocab(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				"word TEXT DEFAULT \"\"," +
				"trans TEXT DEFAULT \"\","+
				"explain TEXT DEFAULT \"\")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
