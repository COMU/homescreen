package com.example.galery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class GaleryDenemeActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
   
    ImageView display;
   	@Override
       public void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.main);
           
           display = (ImageView) findViewById(R.id.IVDisplay);
           ImageView image1 = (ImageView) findViewById(R.id.IVimage1);
           ImageView image2 = (ImageView) findViewById(R.id.IVimage2);
           ImageView image3 = (ImageView) findViewById(R.id.IVimage3);
           ImageView image4 = (ImageView) findViewById(R.id.IVimage4);
           ImageView image5 = (ImageView) findViewById(R.id.IVimage5);
           ImageView image6 = (ImageView) findViewById(R.id.IVimage6);
           ImageView image7 = (ImageView) findViewById(R.id.IVimage7);
           ImageView image8 = (ImageView) findViewById(R.id.IVimage8);
           ImageView image9 = (ImageView) findViewById(R.id.IVimage9);
           
           image1.setOnClickListener(this);
           image2.setOnClickListener(this);
           image3.setOnClickListener(this);
           image4.setOnClickListener(this);
           image5.setOnClickListener(this);
           image6.setOnClickListener(this);
           image7.setOnClickListener(this);
           image8.setOnClickListener(this);
           image9.setOnClickListener(this);
       }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		
		case R.id.IVimage1:
			display.setImageResource(R.drawable.golden0);
			break;
		case R.id.IVimage2:
			display.setImageResource(R.drawable.golden1);
			break;
		case R.id.IVimage3:
			display.setImageResource(R.drawable.golden2);
			break;
		case R.id.IVimage4:
			display.setImageResource(R.drawable.golden3);
			break;
		case R.id.IVimage5:
			display.setImageResource(R.drawable.golden4);
			break;
		case R.id.IVimage6:
			display.setImageResource(R.drawable.golden5);
			break;
		case R.id.IVimage7:
			display.setImageResource(R.drawable.golden6);
			break;
		case R.id.IVimage8:
			display.setImageResource(R.drawable.golden7);
			break;
		case R.id.IVimage9:
			display.setImageResource(R.drawable.golden8);
			break;
		
			
		}
		
	}
}