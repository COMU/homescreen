package com.comu.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	 private Context mContext;

	    public ImageAdapter(Context c) {
	        mContext = c;
	    }

	    public int getCount() {
	    	
	        return 0;//VideoGaleri.length;
	    }

	 
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        TextView textView;
	        imageView = new ImageView(mContext);
	        textView = new TextView(mContext);
	        imageView.setLayoutParams(new GridView.LayoutParams(95, 95));
	        textView.setLayoutParams(new GridView.LayoutParams(95, 95));
	        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);	     
	  //      imageView.setImageResource();
	        textView.setText("");
	        return imageView;
	    }


		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub		
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
 
		

}
