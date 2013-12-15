package br.gabriel.molter.simplecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

	private static final String TABLE_NAME = "studdent";
	
	private static final String CREATE_DB = " CREATE TABLE " + TABLE_NAME + 
			" ( _id INTEGER PRIMARY KEY  NOT NULL ," +
			" name TEXT NOT NULL," +
			" grade TEXT NULL );";
	
	public DbHelper(Context context) {
		
		super(context, "CrudDemo", null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_DB);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
