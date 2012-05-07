package com.comu.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	
	public static String videoList[] = YouTubeActivity.videoInf;
//	public static String videoList[] = {"ayse","emre"};	
	public static int[] dizi={
		R.drawable.ic_launcher,
		R.drawable.icon_search,
		R.drawable.youtube,
		R.drawable.ic_launcher,
		R.drawable.icon_search,
		R.drawable.youtube,
		R.drawable.ic_launcher,
		R.drawable.icon_search,
		R.drawable.youtube
	};
	 private Context mContext;

	    public ImageAdapter(Context c) {
	        mContext = c;
	    }

	    public int getCount() {
	    	
	        return dizi.length;
	    }

	 
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        TextView textView = null;
	        imageView = new ImageView(mContext);
	  //      textView = new TextView(mContext);
	        imageView.setLayoutParams(new GridView.LayoutParams(95, 95));
	 //        textView.setLayoutParams(new GridView.LayoutParams(95, 95));
	        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);	     
	        imageView.setBackgroundResource(dizi[position]);
//	        for(int i=0;i<20;i++){
//	        	textView = new TextView(mContext);
//	        	textView.setText(videoList[i]);
//	        	
//	        }
			//return textView;
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
