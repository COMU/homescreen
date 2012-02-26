package com.comu.android;

import java.lang.reflect.Field;

import com.comu.android.CoverFlowClickableActivity.ImageAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class SubMenuActivity extends Activity implements OnItemClickListener {

	public static int alinanposition=CoverFlowClickableActivity.gelenposition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Called when the activity is first created
		super.onCreate(savedInstanceState);
		setContentView(R.xml.sub_menu);
		
		final CoverFlow coverFlow = (CoverFlow) findViewById(this.getResources().getIdentifier(
	               "coverflow", "id", "com.comu.android"));
			setupCoverFlow(coverFlow);
			final GridView gridview = (GridView) findViewById(R.id.gridView);
		    gridview.setAdapter(new ImageAdapter2(this));
	}
	
	private void setupCoverFlow(CoverFlow coverFlow) {
		// CoverFlow is assigned to the settings
		
		coverFlow.setAdapter(new ImageAdapter(this));
		ImageAdapter coverImageAdapter = new ImageAdapter(this);
		coverFlow.setOnItemClickListener(this);
		coverFlow.setAdapter(coverImageAdapter);       
		coverFlow.setSpacing(-30);
		coverFlow.setSelection(alinanposition-1, true);
		coverFlow.setAnimationDuration(1000);
		
	}
	public static int  iconCounter;
	
	public class ImageAdapter2 extends BaseAdapter {
		 private Context mContext=null;

		    public ImageAdapter2(Context c) {
		        mContext = c;
		    }

		    public int getCount() {//dizinin uzunluğunu fazla döndürdüğümüzde tıklamalarda hata veriyor
		        if (alinanposition==1) return browsers.length;
		        else if(alinanposition==2) return socialNetwork.length;
		        else if (alinanposition==6) return folder.length;
		        return 2;
		    }

		 
		    public View getView(int position, View convertView, ViewGroup parent) {
		        ImageView imageView;
		        imageView = new ImageView(mContext);
		        imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
		        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);	
//		        imageView.setImageResource(browsers[position]);
//		        switch(position1){
//		        case 1:imageView.setImageResource(galeri1[position]);break;
//		        case 2:imageView.setImageResource(galeri[position]);break;
//		        }
		       
		        if(alinanposition==1){
		        	imageView.setImageResource(browsers[position]);
		        	iconCounter=browsers.length;
		        	return imageView;
		        }
		        if(alinanposition==2){
		        	imageView.setImageResource(socialNetwork[position]);
		        	iconCounter= socialNetwork.length;
		        	return imageView;
		        	
		        }
		        if(alinanposition==6){
		        	imageView.setImageResource(folder[position]);
		        	return imageView;
		        }
		        else return imageView;
		    }

		    private Integer[] socialNetwork = {		    				    		
		    		R.raw.facebook,
		    		R.raw.twitter,
		    		R.raw.delicious
		    };

		    private Integer[] browsers = {		    		
		    		R.raw.chrome,
		    		R.raw.firefox
		    };
		    private Integer[] folder = {		    		
		    		R.raw.music,
		    		R.raw.pictures,
		    		R.raw.video
		    };
		    
			@Override
			public Object getItem(int position) {
				// not used		
				return null;
			}

			@Override
			public long getItemId(int position) {
				// not used
				return 0;
			}

			

	}
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

		private ImageView[] mImages;

		public ImageAdapter(Context c) {
			mContext = c;
			mImages = new ImageView[mImageIds.length];
		}

		public boolean createReflectedImages() {

			final int reflectionGap = 4;

			int index = 0;
			for (int imageId : mImageIds) {
				Bitmap originalImage = BitmapFactory.decodeResource(
						getResources(), imageId);
				int width = originalImage.getWidth();
				int height = originalImage.getHeight();

				// This will not scale but will flip on the Y axis
				Matrix matrix = new Matrix();
				matrix.preScale(1, -1);

				// Create a Bitmap with the flip matrix applied to it.
				// We only want the bottom half of the image
				Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
						height / 2, width, height / 2, matrix, false);

				// Create a new bitmap with same width but taller to fit
				// reflection
				Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
						(height + height / 2), Config.ARGB_8888);

				// Create a new Canvas with the bitmap that's big enough for
				// the image plus gap plus reflection
				Canvas canvas = new Canvas(bitmapWithReflection);
				// Draw in the original image
				canvas.drawBitmap(originalImage, 0, 0, null);
				// Draw in the gap
				Paint deafaultPaint = new Paint();
				canvas.drawRect(0, height, width, height + reflectionGap,
						deafaultPaint);
				// Draw in the reflection
				canvas.drawBitmap(reflectionImage, 0, height + reflectionGap,
						null);

				// Create a shader that is a linear gradient that covers the
				// reflection
				Paint paint = new Paint();
				LinearGradient shader = new LinearGradient(0,
						originalImage.getHeight(), 0,
						bitmapWithReflection.getHeight() + reflectionGap,
						0x70ffffff, 0x00ffffff, TileMode.CLAMP);
				// Set the paint to use this shader (linear gradient)
				paint.setShader(shader);
				// Set the Transfer mode to be porter duff and destination in
				paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
				// Draw a rectangle using the paint with our linear gradient
				canvas.drawRect(0, height, width,
						bitmapWithReflection.getHeight() + reflectionGap, paint);

				ImageView imageView = new ImageView(mContext);
				imageView.setImageBitmap(bitmapWithReflection);
				imageView.setLayoutParams(new CoverFlow.LayoutParams(240, 360));
				imageView.setScaleType(ScaleType.MATRIX);
				mImages[index++] = imageView;

			}
			return true;
		}
		public int getCount() {
			return mImageIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public final long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			// Use this code if you want to load from resources
			ImageView i = new ImageView(mContext);
			i.setImageResource(mImageIds[position]);
			i.setLayoutParams(new CoverFlow.LayoutParams(200, 300));
			i.setScaleType(ImageView.ScaleType.FIT_XY);

			// Make sure we set anti-aliasing otherwise we get jaggies
			BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
			drawable.setAntiAlias(true);
			return i;

			// return mImages[position];
		}

		/**
		 * Returns the size (0.0f to 1.0f) of the views depending on the
		 * 'offset' to the center.
		 */
		public float getScale(boolean focused, int offset) {
			/* Formula: 1 / (2 ^ offset) */
			return Math
					.max(0, 1.0f / (float) Math.pow(1 / 2, Math.abs(offset)));
		}
	
	}
	public void onItemClick(AdapterView<?> Gallery, View arg1, int position, long arg3) {
		// Icons in the menu is clicked
		    position++;
		    CoverFlowClickableActivity.gelenposition=position;
		    alinanposition=position;
			switch (position){
			
				case 1:Intent viewIntent1 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				startActivity(viewIntent1);break;
				
				case 2:Intent viewIntent2 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				startActivity(viewIntent2);break;
				
				case 3:Intent viewIntent3 = new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com"));				
				startActivity(viewIntent3);break;
				
				case 4:Intent viewIntent4 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				startActivity(viewIntent4);break;
				
				case 5:Intent viewIntent5 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				startActivity(viewIntent5);break;
	
				case 6:Intent viewIntent6 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				startActivity(viewIntent6);break;
				
				case 7:Intent viewIntent7 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				startActivity(viewIntent7);break;
				
				case 8:Intent viewIntent8 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				startActivity(viewIntent8);break;
				
				case 9:Intent viewIntent9 = new Intent(new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS));				
				startActivity(viewIntent9);break;

				
			}
		     
		     
//			else Toast.makeText(	CoverFlowClickableActivity.this ,+ position + ". icona tiklandi ", position).show();
	}

	
}
