package com.comu.android;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ThemeActivity extends Activity{
		VeriTabani imagepath = new VeriTabani(this);
		
		private String[] SELECT = { "id" };
		
		private Cursor GetData() {
			// get data from database
			SQLiteDatabase db = imagepath.getReadableDatabase();
			Cursor cursor = db.query("temacesitleritablosu", SELECT , null, null,
					null, null, null);
			startManagingCursor(cursor);
			return cursor;
		}
		
		public void updateTemaId(int id){
			// hold theme's id in database
			SQLiteDatabase db = imagepath.getWritableDatabase();
			ContentValues veriler = new ContentValues();
			veriler.put("guncelID", id);
			
			db.update("IDTable", veriler, "id=1" , null);
		}
		
		public void updateSubThemeId(int id) {
			// Auto-generated method stub
			SQLiteDatabase db = imagepath.getWritableDatabase();
			ContentValues veriler = new ContentValues();
			veriler.put("guncelID", id);
			
			db.update("subIDTable", veriler, "id=1" , null);
			
		}
		
		public static int mTemaId;
		public static int mSubThemeId = 4;
		
		protected void onCreate(Bundle savedInstanceState) {
		// Called when the activity is first created
			super.onCreate(savedInstanceState);
			setContentView(R.layout.theme_menu);
	
		
		
		TextView theme1 = (TextView)findViewById(R.id.text1);
		theme1.setText("Grey Theme");
		
		TextView theme2 = (TextView)findViewById(R.id.text2);
		theme2.setText("Blue Theme");
		
		final CheckBox changeTheme = (CheckBox)findViewById(R.id.buton1);
		final CheckBox changeTheme2 = (CheckBox)findViewById(R.id.buton2);
		
		
		if(mTemaId==2){
			changeTheme.setChecked(false);
			changeTheme2.setChecked(true);
		}
		else{
			changeTheme.setChecked(true);
			changeTheme2.setChecked(false);
		}
		
		
		changeTheme.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// Auto-generated method stub	
				mTemaId = 1;
				mSubThemeId = 4;
				changeTheme2.setChecked(false);
				Log.v("DEBUG","mtemaId:" + mTemaId);
				updateTemaId(mTemaId);
				updateSubThemeId(mSubThemeId);
				Intent intent = new Intent(getApplicationContext(),CoverFlowClickableActivity.class);
				startActivity(intent);
				
			}
		});
	
		
		changeTheme2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// Auto-generated method stub	
				mTemaId = 2;
				mSubThemeId = 5;
				changeTheme.setChecked(false);
				updateTemaId(mTemaId);
				updateSubThemeId(mSubThemeId);
				Log.v("DEBUG","mtemaId:" + mTemaId);
				Intent intent = new Intent(getApplicationContext(),CoverFlowClickableActivity.class);
				startActivity(intent);
				
			}
		});
	} 
		
}
	