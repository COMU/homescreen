package com.comu.android;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

public class CoverFlowClickableActivity extends Activity implements OnItemClickListener{
	private VeriTabani imagepath;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//this.setWallpaper();

		
		 
		//*******************************************          son ekleme       
		imagepath = new VeriTabani(this);
		
		        setTitle("Cover Flow  Ekran Gorunumu");
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

		        List<Map<String, Object>> drinfo = new ArrayList<Map<String, Object>>();

		        java.lang.reflect.Field[] drawables = RDrawable.getFields();
		        for(java.lang.reflect.Field dr : drawables) {
		            Map<String, Object> map = new HashMap<String, Object>();
		            Drawable img = getResources().getDrawable(dr.getInt(null));

		            map.put("drimg", dr.getInt(null));
		            map.put("drname", dr.getName());

		            drinfo.add(map);
		            TextView text = (TextView)findViewById(R.id.text);
		            text.setText(dr.getName());   
		            
		            
		    		
					
		    		if(!checkDataBase()){	
		    			try {
		    				 for(java.lang.reflect.Field ids : drawables){
		    					 Drawable resim = getResources().getDrawable(ids.getInt(null));
		    		
		    						KayitEkle(ids.getInt(resim), ids.getName());	    				 
					
		    					}
		    				 }
	    			
		    			finally {
		    				imagepath.close();
		    			}
		    	}
		    		
          
		        }
		    } catch (Exception e) {
		        // TODO: handle exception
		    }
		  
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		//*******************************************          son ekleme
		       
		    
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		final CoverFlow coverFlow = (CoverFlow) findViewById(this.getResources().getIdentifier(
               "coverflow", "id", "com.comu.android"));
		setupCoverFlow(coverFlow);
		

		Button temabutonu = (Button)findViewById(R.id.buton);
		temabutonu.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
//					TextView text = (TextView)findViewById(R.id.text);
//					Field[] IDFields = R.drawable.class.getFields();
//					int[] IDs = new int[IDFields.length - 1];
//					try {
//						
//						for (int i = 0; i < IDFields.length - 1; i++) {
//							IDs[i] = IDFields[i + 1].getInt(null);
//						}
//					} catch (Exception e) {
//						throw new IllegalArgumentException();
//					}					
//					text.setText(IDs[0]);
			
	
				
			}
		});
		
	};
	
	/*****************************/
	
//	Integer[] mImageIds = new Integer[]{paths};
	
	/************************************/
	
	private boolean checkDataBase() {
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

	private void KayitEkle(int i, String etiket) {
		// TODO Auto-generated method stub

		SQLiteDatabase db = imagepath.getWritableDatabase();
		ContentValues veriler = new ContentValues();
		veriler.put("imagepath", i);
		veriler.put("etiket", etiket.toString());

		db.insertOrThrow("temacesitleri", null, veriler);

		try {
//			Cursor cursor = KayitGetir();
//			KayitGoster(cursor);
		} finally {
			imagepath.close();

		}
	}

	private String[] SELECT = { "imagepath","etiket" };

	private Cursor KayitGetir() {
		SQLiteDatabase db = imagepath.getReadableDatabase();
		Cursor cursor = db.query("temacesitleri", SELECT, null, null,
				null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}

	
	private String[] PATH = {"imagepath"};
	
	public Cursor PathGetir(){
		SQLiteDatabase db =imagepath.getReadableDatabase();
		Cursor deneme = db.query("temacesitleri", PATH, null, null, null, null, null);
		startManagingCursor(deneme);
		return deneme;
	}

	
	private void KayitGoster(Cursor cursor) {
		while (cursor.moveToNext()) {
			String yol_adi = cursor.getString(cursor
					.getColumnIndex("imagepath"));
			
			String etiket=cursor.getString(cursor.getColumnIndex("etiket"));		
		}
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

//		Integer[] mImageIds = getAllResourceIDs();
		

//		private Integer[] getAllResourceIDs()
//				throws IllegalArgumentException {
//
//			Field[] IDFields = aClass.getFields();
//			Cursor cursor = PathGetir();
//			ArrayList<Integer> strings = new ArrayList<Integer>();
//			for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
//				Integer paths = Integer.parseInt(cursor.getString(cursor.getColumnIndex("imagepath")));
//				strings.add(paths);	
//			}
//			Integer[] mImage = new Integer[strings.size()];
//			mImage = (Integer[]) strings.toArray();
//			
//			Integer[] IDs = new Integer[mImage.length - 1];
//
//			try {
//				
//				for (int i = 0; i < mImage.length - 1; i++) {
//					IDs[i] = mImage[i + 1];
//				}
//			} catch (Exception e) {
//				throw new IllegalArgumentException();
//			}
//			return IDs;
//		}
//		
//		
		

		Integer[]  mImageIds= { 
				R.drawable.browser,
				R.drawable.sosyalag,
				R.drawable.youtube,
				R.drawable.gmail,
				R.drawable.galeri,
				R.drawable.wikipedia,
				R.drawable.setup,
				R.drawable.settings					
		};
	
	//	Integer[] mImageIds = upgradeImageIds();
		
		
			
		private ImageView[] mImages;
		

		public ImageAdapter(Context c) {
			mContext = c;
			mImages = new ImageView[mImageIds.length];
		}
/*******************/
		private Integer[] upgradeImageIds() {
			// TODO Auto-generated method stub
			
			Cursor cursor = PathGetir();
			ArrayList<Integer> strings = new ArrayList<Integer>();
			Integer[] mImageIds = new Integer[strings.size()];
			for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				Integer paths = Integer.parseInt(cursor.getString(cursor.getColumnIndex("imagepath")));
				
				strings.add(paths);	
			}
			
			mImageIds = (Integer[]) strings.toArray();
			
			return mImageIds;
		}
/**********************/

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
				
				case 8:Intent viewIntent8 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				startActivity(viewIntent8);break;
				
				case 9:
                startActivity(new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS));
                    break;

				
			}
		     
//			else Toast.makeText(	CoverFlowClickableActivity.this ,+ position + ". icona tiklandi ", position).show();
	}
}
