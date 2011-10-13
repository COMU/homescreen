package com.android;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;


public class AndroidUygulamasiActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);     
   
    final GridView gridview = (GridView) findViewById(R.id.gridview);
    gridview.setAdapter(new ImageAdapter(this));
    
    gridview.setOnItemClickListener(new OnItemClickListener() {
    	
    	
		public void onItemClick(AdapterView<?> album, View v, int position,
				long id) {
			// TODO Auto-generated method stub
			
			
			ImageView imageview=(ImageView) findViewById(R.id.imageView);
			ImageView imageView;
			imageView = new ImageView(AndroidUygulamasiActivity.this);		
			imageview.setImageResource(galeri1[position]);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			
		}
    });
     
}
    private Integer[] galeri1 = {
    		R.drawable.icon, R.drawable.image,R.drawable.a,
    		R.drawable.d,R.drawable.e,R.drawable.h,
    		R.drawable.j,R.drawable.n,R.drawable.r,
    		R.drawable.s,R.drawable.t,R.drawable.w,
    		R.drawable.y,R.drawable.z
    		
    		
    };
}