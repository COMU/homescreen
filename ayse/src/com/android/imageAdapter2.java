package com.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class imageAdapter2 extends BaseAdapter{
    private Context mContext;
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public  View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView;
		imageView = new ImageView(mContext);
		imageView.setImageResource(galeri1[position]);
	    return imageView;
		
	}
	private Integer[] galeri1 = {
    		R.drawable.icon, R.drawable.image,R.drawable.a,
    		R.drawable.d,R.drawable.e,R.drawable.h,
    		R.drawable.j,R.drawable.n,R.drawable.r,
    		R.drawable.s,R.drawable.t,R.drawable.w,
    		R.drawable.y,R.drawable.z
    		
    		
    };

}
