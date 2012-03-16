package com.comu.android;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VeriTabani extends SQLiteOpenHelper {
	
	private static final String VERITABANI = "tema.db";
	private static final int SURUM = 1;

	public VeriTabani(Context con){
		super(con, VERITABANI, null, SURUM);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE tema1(id INTEGER PRIMARY KEY AUTOINCREMENT,imagepath TEXT,etiket TEXT)");
		db.execSQL("CREATE TABLE tema2(id INTEGER PRIMARY KEY AUTOINCREMENT,imagepath TEXT,etiket TEXT)");
		db.execSQL("CREATE TABLE temacesitleritablosu(id INTEGER PRIMARY KEY AUTOINCREMENT,temaTabloAdlari TEXT)");
		db.execSQL("CREATE TABLE IDTable(id INTEGER PRIMARY KEY AUTOINCREMENT, guncelID INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXIST tema1");
		db.execSQL("DROP TABLE IF EXIST tema2");
		db.execSQL("DROP TABLE IF EXIST temacesitleritablosu");
		db.execSQL("DROP TABLE IF EXIST IDTable");
		onCreate(db);
	}

}