package com.comu.android;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ThemeActivity extends Activity{
		
	protected void onCreate(Bundle savedInstanceState) {
		// Called when the activity is first created
		super.onCreate(savedInstanceState);
		setContentView(R.layout.theme_menu);
	
		TextView text = (TextView)findViewById(R.id.text1);
		text.setText("Tema1");
		
		TextView text2 = (TextView)findViewById(R.id.text2);
		text2.setText("Tema2");
		
	} 
}
	
