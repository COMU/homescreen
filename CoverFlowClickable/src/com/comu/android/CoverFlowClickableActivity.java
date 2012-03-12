package com.comu.android;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;


public class CoverFlowClickableActivity extends Activity implements OnItemClickListener{
	private VeriTabani imagepath;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//this.setWallpaper();
      
		imagepath = new VeriTabani(this);
		
		if (!checkDataBase()) {
			try {

				Integer[]  resimler = { R.drawable.browser,
						R.drawable.c_sosyalag, R.drawable.d_youtube,
						R.drawable.gmail,R.drawable.galeri,
						R.drawable.oyunlar, R.drawable.wikipedia,
						R.drawable.setup, R.drawable.settings };

				InsertData(resimler[0].toString(), "Browser");
				InsertData(resimler[1].toString(), "Sosyal Aglar");
				InsertData(resimler[2].toString(), "Youtube");
				InsertData(resimler[3].toString(), "Gtalk");
				InsertData(resimler[4].toString(), "Oyunlar");
				InsertData(resimler[5].toString(), "Galeri");
				InsertData(resimler[6].toString(), "Wikipedia");
				InsertData(resimler[7].toString(), "Downloads");
				InsertData(resimler[8].toString(), "Ayarlar");

			} finally {
				imagepath.close();
			}
		}
		
		final CoverFlow coverFlow = (CoverFlow) findViewById(this.getResources().getIdentifier(
               "coverflow", "id", "com.comu.android"));
		setupCoverFlow(coverFlow);
		
	}

	
	private boolean checkDataBase() {
		//TODO make control if existing database
		SQLiteDatabase checkDB = null;
	    try {
	        checkDB = SQLiteDatabase.openDatabase("/data/data/com.comu.android/databases/tema", null,
	                SQLiteDatabase.OPEN_READONLY);
	        checkDB.close();
	    } catch (SQLiteException e) {
	        // database doesn't exist yet.
	    }
	    return checkDB != null ? true : false;
	}

	private void InsertData(String resimler, String etiket) {
		// TODO insert data to database

		SQLiteDatabase db = imagepath.getWritableDatabase();
		ContentValues veriler = new ContentValues();
		veriler.put("imagepath", resimler);
		veriler.put("etiket", etiket.toString());

		db.insertOrThrow("temacesitleri", null, veriler);
	}

	private String[] SELECT = { "imagepath","etiket" };

	private Cursor GetData() {
		//TODO get data from database
		SQLiteDatabase db = imagepath.getReadableDatabase();
		Cursor cursor = db.query("temacesitleri", SELECT, null, null,
				null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}
	

	private void setupCoverFlow(CoverFlow coverFlow) {
		// CoverFlow is assigned to the settings
		
		coverFlow.setAdapter(new ImageAdapter(this));
		ImageAdapter coverImageAdapter = new ImageAdapter(this);
		coverFlow.setOnItemClickListener(this);
		coverFlow.setAdapter(coverImageAdapter);       
		coverFlow.setSpacing(-30);
		coverFlow.setSelection(4, true);
		coverFlow.setAnimationDuration(1000);
		
	}


	public class ImageAdapter extends BaseAdapter {
		int mGalleryItemBackground;
		private Context mContext;

		Integer[] mImageIds = upgradeImageIds();
		String[] mIconName = upgradeIconName();
		
		private ImageView[] mImages;
		

		public ImageAdapter(Context c) {
			mContext = c;
			mImages = new ImageView[mImageIds.length];
		}
		/***************************/
		 
		 private String[] upgradeIconName() {
			String[] iconName = new String[9];
			Cursor cursor = GetData();
			try {
				  Class RClass = Class.forName("com.comu.android.R");
				  Class[] subclasses = RClass.getDeclaredClasses();
				  Class RDrawable = null;
				  for(Class subclass : subclasses) {
					  if("com.comu.android.R.drawable".equals(subclass.getCanonicalName())) {
						  RDrawable = subclass;
						  break;
						  }
					  }
				  java.lang.reflect.Field[] drawables = RDrawable.getFields();
				  int i = 0;
				  for(java.lang.reflect.Field dr : drawables) {
//			       	Log.v("DEBUG", "yol: " + Integer.toString(yol));
//			  		Log.v("DEBUG", "dr: " + Integer.toString(dr.getInt(null)));
					  while (cursor.moveToNext()) {
							
						  String  yol_adi = cursor.getString(cursor.getColumnIndex("imagepath"));
						  Integer yol = Integer.parseInt(yol_adi);
						  String  name = cursor.getString(cursor.getColumnIndex("etiket"));
						  if(dr.getInt(null)==yol);
			  			  {
			  				  iconName[i] = name;
			  				Log.v("DEBUG", "yol: " + Integer.toString(yol));
			  				  Log.v("DEBUG","iconName:"+i+" "+iconName[i]);
			  				  }
			  			  i++;
			  			  }
					  
					  }
				  } catch (Exception e) {
				  		        // TODO: handle exception
					  }
			  return iconName;
		}
		 /****************************/
		
		
		private Integer[] upgradeImageIds() {
			// TODO created an image array with imageIds from the database
			Integer[]  dizi= new Integer[9];
			Cursor cursor = GetData();
			  try {
				  Class RClass = Class.forName("com.comu.android.R");
				  Class[] subclasses = RClass.getDeclaredClasses();
				  Class RDrawable = null;
				  for(Class subclass : subclasses) {
					  if("com.comu.android.R.drawable".equals(subclass.getCanonicalName())) {
						  RDrawable = subclass;
						  break;
						  }
					  }
				  java.lang.reflect.Field[] drawables = RDrawable.getFields();
				  int i = 0;
				  for(java.lang.reflect.Field dr : drawables) {
//					  Log.v("DEBUG", "yol: " + Integer.toString(yol));
//			  		  Log.v("DEBUG", "dr: " + Integer.toString(dr.getInt(null)));
					  while (cursor.moveToNext()) {
						  String  yol_adi = cursor.getString(cursor.getColumnIndex("imagepath"));
						  Integer yol = Integer.parseInt(yol_adi);
						  if(dr.getInt(null)==yol);
			  			  {
			  				  dizi[i]=yol;
			  				  }
			  			  i++;
			  			  }
					  }
				  } catch (Exception e) {
				  		        // TODO: handle exception
					  }
			  return dizi;
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

	public static int gelenposition;
	public void onItemClick(AdapterView<?> Gallery, View arg1, int position, long arg3) {
		// Icons in the menu is clicked
		    position++;
		    gelenposition=position;
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
				
				case 8:startActivity(new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS));
                break;
				
				case 9:Intent viewIntent9 = new Intent(getApplicationContext(), ThemeActivity.class);				
				startActivity(viewIntent9);break;

                
			}
	}
}
