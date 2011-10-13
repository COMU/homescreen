package com.example.galery;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	int mGalleryItemBackground;
	private Context mContext;
	
	int[] mImageIds = getAllResourceIDs(R.drawable.class);
	
	private int[] getAllResourceIDs(Class<?> aClass)
			throws IllegalArgumentException {

		Field[] IDFields = aClass.getFields();

		int[] IDs = new int[IDFields.length - 1];

		try {

			for (int i = 0; i < IDFields.length - 1; i++) {
				IDs[i] = IDFields[i + 1].getInt(null);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		return IDs;
	}
	
//	private Integer[] mImageIds = {
	//		R.drawable.golden0,
	//		R.drawable.golden1,
	//		R.drawable.golden2,
	//		R.drawable.golden3,
	//		R.drawable.golden4,
	//		R.drawable.golden5
//	};
	
	
	public ImageAdapter(Context c) {
		mContext = c;
		TypedArray attr = mContext.obtainStyledAttributes(R.styleable.GaleryDenemeActivity);
		mGalleryItemBackground = attr.getResourceId(R.styleable.GaleryDenemeActivity_android_galleryItemBackground, 0);
		attr.recycle();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mImageIds.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(mImageIds[position]);
		imageView.setLayoutParams(new Gallery.LayoutParams(150, 100));
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		imageView.setBackgroundResource(mGalleryItemBackground);
		
		return imageView;
	}

}
