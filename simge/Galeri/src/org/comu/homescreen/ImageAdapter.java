package org.comu.homescreen;

import java.lang.reflect.Field;

import org.comu.homescreen.R;


import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter {
    /** The parent context */
    private Context myContext;
    int gallery_background;

    int[] allImage = getAllImage(R.drawable.class);
    
    public ImageAdapter(Context c) {
    	myContext = c; 
    	
    	TypedArray attr = myContext.obtainStyledAttributes(org.comu.homescreen.R.styleable.Galeri);
		gallery_background = attr.getResourceId(R.styleable.Galeri_android_galleryItemBackground, 0);
		attr.recycle();
         
    }
    
    private int[] getAllImage(Class<?> class1) throws IllegalArgumentException {
		// TODO Auto-generated method stub
    	Field[] dizi = class1.getFields();
    	
    	int[] id = new int[dizi.length];

		try {

			for (int i = 0; i < dizi.length - 1; i++) {
				id[i] = dizi[i + 1].getInt(null);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		return id;
	}
	

	public int getCount() { return this.allImage.length; }
    
    public Object getItem(int position) { return position; }
    public long getItemId(int position) { return position; }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView i= new ImageView(this.myContext);
		
		i.setImageResource(this.allImage[position]);
		
		i.setScaleType(ImageView.ScaleType.FIT_XY);
		
		i.setLayoutParams(new Gallery.LayoutParams(300, 300));
		
		i.setBackgroundResource(gallery_background);
        
		return i;
		
	}
	
	 public float getScale(boolean focused, int offset) {
         /* Formula: 1 / (2 ^ offset) */
     return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset)));
 }

	}