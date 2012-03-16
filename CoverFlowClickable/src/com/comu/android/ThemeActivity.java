package com.comu.android;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ThemeActivity extends Activity{
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
		
		public static int mTemaId = 1;
		
		protected void onCreate(Bundle savedInstanceState) {
		// Called when the activity is first created
			super.onCreate(savedInstanceState);
			setContentView(R.layout.theme_menu);
	
		
		
		TextView text = (TextView)findViewById(R.id.text1);
		text.setText("Tema1");
		
		TextView text2 = (TextView)findViewById(R.id.text2);
		text2.setText("Tema2");
		
		Button changeTheme = (Button)findViewById(R.id.buton1);
		changeTheme.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				mTemaId = 1;
				Log.v("DEBUG","mtemaId:" + mTemaId);
				Intent intent = new Intent(getApplicationContext(),CoverFlowClickableActivity.class);
				startActivity(intent);
				
			}
		});
	
		Button changeTheme2 = (Button)findViewById(R.id.buton2);
		changeTheme2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				mTemaId = 2;
				Log.v("DEBUG","mtemaId:" + mTemaId);
				Intent intent = new Intent(getApplicationContext(),CoverFlowClickableActivity.class);
				startActivity(intent);
				
			}
		});
	} 
		
}
	
