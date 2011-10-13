package com.example.galery;

//import org.comu.HomeScreen.ImageAdapter;
//import org.comu.HomeScreen.R;

//import org.comu.HomeScreen.R;

//import org.comu.HomeScreen.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class GaleryDenemeActivity extends Activity {
    /** Called when the activity is first created. */
   
    ImageView display;
   	@Override
       public void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.main);
      
           final Animation scale = AnimationUtils.loadAnimation(this,
   				R.anim.scalee);
           
           Gallery gallery = (Gallery) findViewById(R.id.galery);
           final ImageAdapter ia = new ImageAdapter(this);
           
           gallery.setAdapter(ia);
           
           gallery.setOnItemClickListener(new OnItemClickListener() {
           	public void onItemClick(AdapterView parent, View v, int position, long id){
           		//Toast.makeText(Galery1Activity.this, ""+ position, Toast.LENGTH_SHORT).show();
           		ImageView resim = (ImageView) findViewById(R.id.IVDisplay);
           		resim.setImageResource(ia.mImageIds[position]);
           		resim.startAnimation(scale);
           	}
   		});
 
		
	}
}