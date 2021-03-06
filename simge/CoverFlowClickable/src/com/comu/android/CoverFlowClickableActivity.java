package com.comu.android;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
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
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;



public class CoverFlowClickableActivity extends BetterActivity implements OnItemClickListener{
	
	private VeriTabani imagepath;
	
	
//	SharedPreferences mySharedPrefs;
//	SharedPreferences.Editor sEditor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	

		final LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);	
		mainLayout.setBackgroundResource(R.drawable.wallpaper_blue);
		
		

		imagepath = new VeriTabani(this);
		
		//*********************************SharedPreferences*********************
				
//		mySharedPrefs = (SharedPreferences) getSharedPreferences("sharedPrefs",MODE_PRIVATE);
//		sEditor = mySharedPrefs.edit();
//		int mTemaId = updateId();
//		Log.v("DEBUG","CmTemaId: "+mTemaId);
//		sEditor.putInt("mtemaId", mTemaId);
//		sEditor.commit();

//		****************************************************************
		if (!checkDataBase()) {
			try {

				Integer[]  resimler = { R.drawable.sitesicon, R.drawable.chatsicon, R.drawable.youtubethreed,
						R.drawable.mailicon, R.drawable.photosicon, R.drawable.gamesicon, R.drawable.wikipediaicon,
						R.drawable.torrenticon, R.drawable.systempreferencesicon
						 };


				InsertData("greyTheme", resimler[0].toString(), "Tarayıcı");
				InsertData("greyTheme", resimler[1].toString(), "Sosyal Aglar");
				InsertData("greyTheme", resimler[2].toString(), "Youtube");
				InsertData("greyTheme", resimler[3].toString(), "Gmail");
				InsertData("greyTheme", resimler[4].toString(), "Galeri");
				InsertData("greyTheme", resimler[5].toString(), "Oyunlar");
				InsertData("greyTheme", resimler[6].toString(), "Wikipedia");
				InsertData("greyTheme", resimler[7].toString(), "İndirilenler");
				InsertData("greyTheme", resimler[8].toString(), "Ayarlar");
				
				Integer[] resimler2 = {R.drawable.bluetheme1,
						R.drawable.bluetheme2, R.drawable.bluetheme3,
						R.drawable.bluetheme4, R.drawable.bluetheme5,
						R.drawable.bluetheme6, R.drawable.bluetheme7, 
						R.drawable.bluetheme8, R.drawable.bluetheme9};
				
				InsertData("blueTheme",resimler2[0].toString(), "Tarayıcı");
				InsertData("blueTheme",resimler2[1].toString(), "Sosyal Aglar");
				InsertData("blueTheme",resimler2[2].toString(), "Youtube");
				InsertData("blueTheme",resimler2[3].toString(), "Gmail");
				InsertData("blueTheme",resimler2[4].toString(), "Galeri");
				InsertData("blueTheme",resimler2[5].toString(), "Oyunlar");
				InsertData("blueTheme",resimler2[6].toString(), "Wikipedia");
				InsertData("blueTheme",resimler2[7].toString(), "İndirilenler");
				InsertData("blueTheme",resimler2[8].toString(), "Ayarlar");
				
				Integer[] gameIcon = {R.drawable.gameicon1, R.drawable.gameicon2, R.drawable.gameicon3,
						R.drawable.gameicon4, R.drawable.gameicon5, R.drawable.gameicon6,
						R.drawable.gameicon7, R.drawable.gameicon8 };
				
				InsertData("gameIconTable", gameIcon[0].toString(), "Frozen Bubble");
				InsertData("gameIconTable", gameIcon[1].toString(), "Unlock Me");
				InsertData("gameIconTable", gameIcon[2].toString(), "Backgammon");
				InsertData("gameIconTable", gameIcon[3].toString(), "Snake");
				InsertData("gameIconTable", gameIcon[4].toString(), "Solitaire");
				InsertData("gameIconTable", gameIcon[5].toString(), "Tetris");
				InsertData("gameIconTable", gameIcon[6].toString(), "Sudoku");
				InsertData("gameIconTable", gameIcon[7].toString(), "Puzzle");
				
				Integer[] socialNetworkIcon = {R.drawable.facebookicon, R.drawable.twittericon, R.drawable.gtalkicon};
				
				InsertData("socialIconTable", socialNetworkIcon[0].toString(), "Facebook");
				InsertData("socialIconTable", socialNetworkIcon[1].toString(), "Twitter");
				InsertData("socialIconTable", socialNetworkIcon[2].toString(), "GTalk");
				
				Integer[] eggsocialNetworkIcon = {R.drawable.facebook, R.drawable.twitter, R.drawable.gtalk};
				
				InsertData("eggsocialIconTable", eggsocialNetworkIcon[0].toString(), "Facebook");
				InsertData("eggsocialIconTable", eggsocialNetworkIcon[1].toString(), "Twitter");
				InsertData("eggsocialIconTable", eggsocialNetworkIcon[2].toString(), "GTalk");
			
				Integer[] GalleryIcon = {R.drawable.musicicon, R.drawable.picturesicon, R.drawable.videoicon};
				
				InsertData("galleryIconTable", GalleryIcon[0].toString(), "Müzik");
				InsertData("galleryIconTable", GalleryIcon[1].toString(), "Resimler");
				InsertData("galleryIconTable", GalleryIcon[2].toString(), "Video");
				

				Integer[] PastelisIcon = {R.drawable.hpmusic, R.drawable.hpphotos, R.drawable.hpvideo};
				
				InsertData("pastelisIconTable", PastelisIcon[0].toString(), "Müzik");
				InsertData("pastelisIconTable", PastelisIcon[1].toString(), "Resimler");
				InsertData("pastelisIconTable", PastelisIcon[2].toString(), "Video");
				
				Integer[] WallpaperList = { R.drawable.wallpaper_grey, R.drawable.wallpaper_blue};
				
				InsertData("Wallpaper", WallpaperList[0].toString(), "Grey");
				InsertData("WallpaperBlue", WallpaperList[1].toString(), "Blue");
						
				
				InsertTableName("greyTheme");
				InsertTableName("blueTheme");
				InsertTableName("gameIconTable");
				InsertTableName("socialIconTable");
				InsertTableName("galleryIconTable");
				InsertTableName("pastelisIconTable");
				InsertTableName("Wallpaper");
				InsertTableName("WallpaperBlue");
				
				InsertID("IDTable", 1);
				InsertID("subIDTable", 4);
				InsertID("WallIDTable",7);
				
			} finally {
				imagepath.close();
			}
		}
		
		final CoverFlow coverFlow = (CoverFlow) findViewById(this.getResources().getIdentifier(
               "coverflow", "id", "com.comu.android"));
		setupCoverFlow(coverFlow);
	
	}
	
	
	
	private void InsertID(String table, int id){
		// insert table name to database
		SQLiteDatabase db = imagepath.getWritableDatabase();
		ContentValues veriler = new ContentValues();
		veriler.put("guncelID", id);	

		db.insertOrThrow(table, null, veriler);
	}
	
	public void updateTemaId(int id){
		// hold theme's id in database
		SQLiteDatabase db = imagepath.getWritableDatabase();
		ContentValues veriler = new ContentValues();
		veriler.put("guncelID", id);
		
		db.update("IDTable", veriler, "id=1" , null);
		
	}
	
	
	public static int updateId(){
		// after pressed the theme button on ThemeActivity class, update temacesitleritablosu's ids 
		 int id = ThemeActivity.mTemaId;
		 return id;	
	}
	
	private void InsertTableName(String name){
		// insert table name to database
		SQLiteDatabase db = imagepath.getWritableDatabase();
		ContentValues veriler = new ContentValues();
		veriler.put("temaTabloAdlari", name);	

		db.insertOrThrow("temacesitleritablosu", null, veriler);
	}


	private boolean checkDataBase() {
		// make control if existing database
		SQLiteDatabase checkDB = null;
	    try {
	        checkDB = SQLiteDatabase.openDatabase("/data/data/com.comu.android/databases/tema.db", null,
	                SQLiteDatabase.OPEN_READONLY);
	        checkDB.close();
	    } catch (SQLiteException e) {
	        // database doesn't exist yet.
	    }
	    return checkDB != null ? true : false;
	}

	private void InsertData(String table, String resimler, String etiket) {
		// insert data to database

		SQLiteDatabase db = imagepath.getWritableDatabase();
		ContentValues veriler = new ContentValues();
		veriler.put("imagepath", resimler);
		veriler.put("etiket", etiket.toString());

		db.insertOrThrow(table , null, veriler);
	}

	private String[] SELECT = { "imagepath","etiket" };

	private Cursor GetData(String table) {
		// get data from database
		SQLiteDatabase db = imagepath.getReadableDatabase();
		Cursor cursor = db.query(table, SELECT, null, null,
				null, null, null);
		startManagingCursor(cursor);
		return cursor;
	}
	
	private String[] ThemeID = { "guncelID" };
	
	private Cursor GetDataID(String table) {
		// get data from database
		SQLiteDatabase db = imagepath.getReadableDatabase();
		Cursor cursor = db.query(table, ThemeID, null, null,
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
		coverFlow.setSpacing(-10);
		coverFlow.setSelection(SubMenuActivity.currentPosition, true);
//		coverFlow.setSelection(5, true);
		coverFlow.setAnimationDuration(1000);	

	}


	public class ImageAdapter extends BaseAdapter {
		int mGalleryItemBackground;
		private Context mContext;

		Integer[] mImageIds = upgradeImageIds();

		
		private ImageView[] mImages;
		

		public ImageAdapter(Context c) {
			mContext = c;
			mImages = new ImageView[mImageIds.length];
		}

		
		public Integer[] theme(){
			Integer[] arrayID = new Integer[1];
			Cursor theme = GetDataID("IDTable");
			while(theme.moveToFirst()){
				int i=0;
				int themeID = theme.getInt(theme.getColumnIndex("guncelID"));
				arrayID[i] = themeID;
				Log.v("DEBUG", "which theme:" + themeID);
				break;
			}
			return arrayID;
		}
		
		
		
		//*******************************************
		private Integer[] upgradeImageIds() {
			// created an image array with imageIds from the database
			Integer[] dizi= new Integer[9];
//			int themeID = updateId();
			Integer[] themeID  = theme();
			Log.v("DEBUG", "theme state :" + themeID[0]);
			if(themeID[0] == 1){
				Cursor cursor = GetData("greyTheme");
				try {
				  int i=0;
				  while (cursor.moveToNext()) {
					  String  yol_adi = cursor.getString(cursor.getColumnIndex("imagepath"));
					  Integer yol = Integer.parseInt(yol_adi);
			  		  dizi[i]=yol;
			  		  Log.v("DEBUG", "dizi: " + Integer.toString(dizi[i]));
			  		  i++;
			  	  }
					  
				  } catch (Exception e) {
					  // handle exception
					  }
			  }
			else if(themeID[0] == 2){
				Cursor cursor = GetData("blueTheme");
				 try {
					  int i=0;
					  while (cursor.moveToNext()) {
						  String  yol_adi = cursor.getString(cursor.getColumnIndex("imagepath"));
						  Integer yol = Integer.parseInt(yol_adi);
				  		  dizi[i]=yol;
				  		  Log.v("DEBUG", "dizi: " + Integer.toString(dizi[i]));
				  		  i++;
				  	  }
						  
					  } catch (Exception e) {
						  // handle exception
						  }	
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
 
	public void onItemClick(AdapterView<?> Gallery, View arg1, int position, long arg3) {
		// Icons in the menu is clicked
		String[] dizi={"Tarayıcı", "Sosyal Aglar","Youtube","Gmail","Galeri","Oyunlar", "Wikipedia", "indirilenler","Ayarlar"};
		Toast.makeText(getApplicationContext(), ""+dizi[position], Toast.LENGTH_LONG).show();
	
		position++;
		
			switch (position){
			
			   	case 1:startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.google.com")));
			   	SubMenuActivity.currentPosition=position-1;
			   	break;
				
				case 2:Intent viewIntent2 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				SubMenuActivity.currentPosition=position-1;
				startActivity(viewIntent2);break;
				
				case 3:Intent viewIntent3 = new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com"));				
				startActivity(viewIntent3);
				SubMenuActivity.currentPosition=position-1;
				break;
				
				case 4:startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://mail.google.com/mail/")));
				SubMenuActivity.currentPosition=position-1;
				break;
				
				case 5:Intent viewIntent5 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				startActivity(viewIntent5);
				SubMenuActivity.currentPosition=position-1;
				break;
	
				case 6:Intent viewIntent6 = new Intent(getApplicationContext(), GameSubMenu.class);				
				startActivity(viewIntent6);
				SubMenuActivity.currentPosition=position-1;
				break;
				
				case 7:startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://tr.wikipedia.org/wiki/Ana_Sayfa")));
				SubMenuActivity.currentPosition=position-1;
				break;
				
				case 8:startActivity(new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS));
				SubMenuActivity.currentPosition=position-1;
				break;
				
				case 9:Intent viewIntent9 = new Intent(getApplicationContext(), ThemeActivity.class);				
				startActivity(viewIntent9);
				SubMenuActivity.currentPosition=position-1;
				break;

                
			}
	}
//	public static void OnFocusChangeListener(ImageAdapter adapter){
//		
//	
//		
//		
//		
//		
//	}

}