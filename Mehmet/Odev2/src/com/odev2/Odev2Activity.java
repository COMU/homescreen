package com.odev2;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class Odev2Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       final Animation scale= AnimationUtils.loadAnimation(this, com.odev2.R.anim.buyuyerek_gelen_ben_ekledim);
        
        Gallery g =(Gallery)findViewById(com.odev2.R.id.galery);
        final ImageAdapter a=new ImageAdapter(this);
    g.setAdapter(a);
    g.setOnItemClickListener(new OnItemClickListener() {
		
    	public void onItemClick(AdapterView parent,View v,int position, long id){
    		Toast.makeText(Odev2Activity.this, "" + position, Toast.LENGTH_SHORT).show();
    		
    	
    		ImageView resim=(ImageView)findViewById(com.odev2.R.id.resim);
            
    		resim.setImageResource(a.mimages[position]);
   		resim.startAnimation(scale);
    	
    	}
	});
    
    }
    
    public class ImageAdapter extends BaseAdapter{

		 View ImageView = null;
		int galary_background;
		private Context mcontext;

		
	public  Integer[] mimages={
			com.odev2.R.drawable.sample_1,
			com.odev2.R.drawable.sample_2,
			com.odev2.R.drawable.sample_3,
			com.odev2.R.drawable.sample_4,
			com.odev2.R.drawable.sample_5,
			com.odev2.R.drawable.sample_6,
			com.odev2.R.drawable.sample_7,
			com.odev2.R.drawable.sample_8,
			com.odev2.R.drawable.sample_9,
			com.odev2.R.drawable.sample_10,
			com.odev2.R.drawable.sample_11,
			com.odev2.R.drawable.sample_12,
			com.odev2.R.drawable.sample_13,
			com.odev2.R.drawable.sample_14,
			
			
		};	

	
		public ImageAdapter(Context c){
			mcontext=c;
			
			TypedArray attr=mcontext.obtainStyledAttributes(com.odev2.R.styleable.Odev2);
			
			galary_background=attr.getResourceId(com.odev2.R.styleable.Odev2_android_galleryItemBackground, 1);
			attr.recycle();
			
		
		}
				
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ImageView imageView = new ImageView(mcontext);

	        imageView.setImageResource(mimages[position]);
	        imageView.setLayoutParams(new Gallery.LayoutParams(300,200));
	        
	        imageView.setBackgroundResource( galary_background);

	        return imageView;

		}
		@Override
		public int getCount() {
			
			return mimages.length;
		}
		@Override
		public Object getItem(int position) {	
			return position;
		}
		@Override
		public long getItemId(int position) {
		
			return position;	
		}		
		
   
    }
}
