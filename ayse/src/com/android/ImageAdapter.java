package com.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	 private Context mContext;

	    public ImageAdapter(Context c) {
	        mContext = c;
	    }

	    public int getCount() {
	        return galeri.length;
	    }

	 
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        imageView = new ImageView(mContext);
	        imageView.setLayoutParams(new GridView.LayoutParams(95, 95));
	        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);	     
	        imageView.setImageResource(galeri[position]);
	        return imageView;
	    }

	 
	    private Integer[] galeri = {
	    		R.drawable.icon, R.drawable.image,R.drawable.a,
	    		R.drawable.d,R.drawable.e,R.drawable.h,
	    		R.drawable.j,R.drawable.n,R.drawable.r,
	    		R.drawable.s,R.drawable.t,R.drawable.w,
	    		R.drawable.y,R.drawable.z
	    		
	    		
	    };

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
