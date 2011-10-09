package com.odev2;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter.AllCaps;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import java.lang.reflect.Field;

import com.odev2.R.drawable;

public class ImageAdapter extends BaseAdapter {

	View ImageView = null;
	int galary_background;
	private Context mcontext;

	/*
	 * public Integer[] mimages = { 
	 * com.odev2.R.drawable.sample_1,
	 * com.odev2.R.drawable.sample_2, 
	 * com.odev2.R.drawable.sample_3,
	 * com.odev2.R.drawable.sample_4, 
	 * com.odev2.R.drawable.sample_5,
	 * com.odev2.R.drawable.sample_6, 
	 * com.odev2.R.drawable.sample_7,
	 * com.odev2.R.drawable.sample_8, 
	 * com.odev2.R.drawable.sample_9,
	 * com.odev2.R.drawable.sample_10,
	 *  com.odev2.R.drawable.sample_11,
	 * com.odev2.R.drawable.sample_12,
	 *  com.odev2.R.drawable.sample_13,
	 * com.odev2.R.drawable.sample_14,
	 * 
	 * };
	 */

	int[] allDrawableIDs = getAllResourceIDs(R.drawable.class);

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

	public ImageAdapter(Context c) {
		mcontext = c;

		TypedArray attr = mcontext.obtainStyledAttributes(com.odev2.R.styleable.Odev2);

		galary_background = attr.getResourceId(
				com.odev2.R.styleable.Odev2_android_galleryItemBackground, 1);
		attr.recycle();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ImageView imageView = new ImageView(mcontext);

		imageView.setImageResource(allDrawableIDs[position]);
		imageView.setLayoutParams(new Gallery.LayoutParams(300, 200));

		imageView.setBackgroundResource(galary_background);

		return imageView;

	}

	@Override
	public int getCount() {

		return allDrawableIDs.length;
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
