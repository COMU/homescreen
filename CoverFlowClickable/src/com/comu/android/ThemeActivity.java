package com.comu.android;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ThemeActivity extends BetterActivity{
		VeriTabani imagepath = new VeriTabani(this);
		
		private String[] SELECT = { "id" };
		
		private Cursor GetData() {
			//TODO get data from database
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
			// TODO Auto-generated method stub
			SQLiteDatabase db = imagepath.getWritableDatabase();
			ContentValues veriler = new ContentValues();
			veriler.put("guncelID", id);
			
			db.update("subIDTable", veriler, "id=1" , null);
			
		}
		
		public static int mTemaId = 1;
		public static int mSubThemeId = 4;
		
		protected void onCreate(Bundle savedInstanceState) {
		// Called when the activity is first created
			super.onCreate(savedInstanceState);
			setContentView(R.layout.theme_menu);
	
		
		
		TextView text = (TextView)findViewById(R.id.text1);
		text.setText("Default Grey Theme");
		
		TextView text2 = (TextView)findViewById(R.id.text2);
		text2.setText("Blue Theme");
		
		Button changeTheme = (Button)findViewById(R.id.buton1);
		changeTheme.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				mTemaId = 1;
				mSubThemeId = 4;
				Log.v("DEBUG","mtemaId:" + mTemaId);
				updateTemaId(mTemaId);
				updateSubThemeId(mSubThemeId);
				Intent intent = new Intent(getApplicationContext(),CoverFlowClickableActivity.class);
				startActivity(intent);
				
			}
		});
	
		Button changeTheme2 = (Button)findViewById(R.id.buton2);
		changeTheme2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				mTemaId = 2;
				mSubThemeId = 5;
				updateTemaId(mTemaId);
				updateSubThemeId(mSubThemeId);
				Log.v("DEBUG","mtemaId:" + mTemaId);
				Intent intent = new Intent(getApplicationContext(),CoverFlowClickableActivity.class);
				startActivity(intent);
				
			}
		});
	} 
		
}
	
