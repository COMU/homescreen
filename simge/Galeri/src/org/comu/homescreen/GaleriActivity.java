package org.comu.homescreen;

import java.lang.reflect.Field;

import org.comu.homescreen.R;
import org.comu.homescreen.R.drawable;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class GaleriActivity extends Activity{
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Animation scale = AnimationUtils.loadAnimation(this,
				org.comu.homescreen.R.anim.animation);
				
				
        Gallery g = (Gallery) findViewById(R.id.gallery);
        final ImageAdapter image = new ImageAdapter(this);
        g.setAdapter(image);       
        g.setOnItemClickListener(new OnItemClickListener() {

    		public void onItemClick(AdapterView parent, View v, int position,
    				long id) {
    			ImageView resim = (ImageView) findViewById(R.id.resim);
    			resim.setImageResource(image.allImage[position]);
    			resim.startAnimation(scale);

    		}
    	});
    }
}