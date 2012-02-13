package org.comu.homescreen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VeriTabani extends SQLiteOpenHelper {
	
	private static final String VERITABANI = "temalar";
	private static final int SURUM = 1;

	public VeriTabani(Context con){
		super(con, VERITABANI, null, SURUM);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE temacesitleri(id INTEGER PRIMARY KEY AUTOINCREMENT,imagepath TEXT)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXIST temacesitleri");
		onCreate(db);
	}

}
