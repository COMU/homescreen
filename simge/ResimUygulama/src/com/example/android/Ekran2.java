package com.example.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ekran2 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ekran2);
		
		final TextView text = (TextView) findViewById(R.id.text);
		
		Button buton = (Button) findViewById(R.id.buton);
		
		buton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.example.android.ResimUygulamaActivity"));
				
				
			}
		});
	}
	
	

}
