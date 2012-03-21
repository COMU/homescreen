package com.comu.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.TableRow.LayoutParams;

public class SubMenuActivity extends Activity implements OnItemClickListener {
int temp=0;
	
	public static int alinanposition=CoverFlowClickableActivity.gelenposition;
	VeriTabani imagepath = new VeriTabani(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Called when the activity is first created
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_menu);
		
		final CoverFlow coverFlow = (CoverFlow) findViewById(this.getResources().getIdentifier(
	               "coverflow", "id", "com.comu.android"));
			setupCoverFlow(coverFlow);
		
		final Integer[] socialNetwork = {		    				    		
		    		R.raw.facebook,
		    		R.raw.twitter,
		    		R.raw.gtalk
		    };
		final String[] SocialIcons={"Facebook","Twitter","GTalk"};
		
		

	    final Integer[] folder = {		    		
		    		R.raw.music,
		    		R.raw.pictures,
		    		R.raw.video
		    };
	    final String[] FolderIcons={"Müzik","Resim","Video"};
	    

	    final TableLayout table = (TableLayout) findViewById(R.id.tablelayout);
		table.setStretchAllColumns(true); 
		  TableRow tr1 = new TableRow(this);
		  tr1.setLayoutParams(new LayoutParams(
                  LayoutParams.FILL_PARENT,
                  120));
		  tr1.setGravity(Gravity.CENTER);
		  TableRow tr2 = new TableRow(this);
		  tr2.setLayoutParams(new LayoutParams(
                  LayoutParams.FILL_PARENT,
                  40));
		  tr2.setGravity(Gravity.CENTER);
		  TableRow tr3 = new TableRow(this);
		  tr3.setLayoutParams(new LayoutParams(
                  LayoutParams.FILL_PARENT,
                  120));
		  tr3.setGravity(Gravity.CENTER);
		  TableRow tr4 = new TableRow(this);
		  tr4.setLayoutParams(new LayoutParams(
                  LayoutParams.FILL_PARENT,
                  40));
		  tr4.setGravity(Gravity.CENTER);
		  ImageView img1 = new ImageView(this);
		  img1.setLayoutParams(new LayoutParams(
                  80,
                  80));
		  ImageView img2 = new ImageView(this);
		  img2.setLayoutParams(new LayoutParams(
                  80,
                  80));
		  ImageView img3 = new ImageView(this);
		  img3.setLayoutParams(new LayoutParams(
                  80,
                  80));
		  TextView it1 = new TextView(this);
		  it1.setLayoutParams(new LayoutParams(
                  80,
                  40));
		  it1.setTextSize(TypedValue.DENSITY_DEFAULT, 25);
		  it1.setGravity(Gravity.CENTER);
		  TextView it2 = new TextView(this);
		  it2.setLayoutParams(new LayoutParams(
                  80,
                  40));
		  it2.setTextSize(TypedValue.DENSITY_DEFAULT, 25);
		  it2.setGravity(Gravity.CENTER);
		  TextView it3 = new TextView(this);
		  it3.setLayoutParams(new LayoutParams(
                  80,
                  40));
		  it3.setTextSize(TypedValue.DENSITY_DEFAULT, 25);
		  it3.setGravity(Gravity.CENTER);
		  if(alinanposition==2){
			 
			  it1.setText(SocialIcons[0]);
			  it2.setText(SocialIcons[1]);
			  it3.setText(SocialIcons[2]);
			  img1.setImageResource(socialNetwork[0]);
			  img2.setImageResource(socialNetwork[1]);
			  img3.setImageResource(socialNetwork[2]);
		  }
          if(alinanposition==5){
        	  it1.setText(FolderIcons[0]);
			  it2.setText(FolderIcons[1]);
			  it3.setText(FolderIcons[2]);
			  img1.setImageResource(folder[0]);
			  img2.setImageResource(folder[1]);
			  img3.setImageResource(folder[2]);
		  }	
         

          tr1.addView(img1);
          tr2.addView(it1);
          tr1.addView(img2);          
          tr2.addView(it2);
          tr3.addView(img3);
          tr4.addView(it3);
          table.addView(tr1);
          table.addView(tr2);
          table.addView(tr3);
          table.addView(tr4);
	         
			
	}
	
	public static int updateId(){
		//TODO after pressed the theme button on ThemeActivity class, update temacesitleritablosu's ids 
		 int id = ThemeActivity.mTemaId;
		 return id;	
	}
	
	private void setupCoverFlow(CoverFlow coverFlow) {
		// CoverFlow is assigned to the settings
		
		coverFlow.setAdapter(new ImageAdapter(this));
		ImageAdapter coverImageAdapter = new ImageAdapter(this);
		coverFlow.setOnItemClickListener(this);
		coverFlow.setAdapter(coverImageAdapter);       
		coverFlow.setSpacing(-10);
		coverFlow.setSelection(alinanposition-1, true);
		coverFlow.setAnimationDuration(1000);
		
	}

	public class ImageAdapter extends BaseAdapter {
		int mGalleryItemBackground;
		private Context mContext;
		
		Integer[] mImageIds = upgradeImageIds();
		
		public Integer[] theme(){
			Integer[] arrayID = new Integer[1];
			Cursor theme = GetDataID("IDTable");
			while(theme.moveToFirst()){
				int i=0;
				int themeID = theme.getInt(theme.getColumnIndex("guncelID"));
				arrayID[i] = themeID;
				Log.v("DEBUG", "hangi tema:" + themeID);
				break;
			}
			return arrayID;
		}
		
		private Integer[] upgradeImageIds() {
			// TODO created an image array with imageIds from the database
			Integer[] dizi= new Integer[9];
//			int themeID = updateId();
			Integer[] themeID  = theme();
			Log.v("DEBUG", "theme durumu :" + themeID[0]);
			if(themeID[0] == 1){
				Cursor cursor = GetData("tema1");
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
					  // TODO: handle exception
					  }
			  }
			else if(themeID[0] == 2){
				Cursor cursor = GetData("tema2");
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
						  // TODO: handle exception
						  }	
			}
			  return dizi;	  
		}
			

		private String[] SELECT = { "imagepath","etiket" };
		
		private Cursor GetData(String table) {
			//TODO get data from database
			SQLiteDatabase db = imagepath.getReadableDatabase();
			Cursor cursor = db.query(table, SELECT, null, null,
					null, null, null);
			startManagingCursor(cursor);
			return cursor;
		}

		private String[] ThemeID = { "guncelID" };
		
		private Cursor GetDataID(String table) {
			//TODO get data from database
			SQLiteDatabase db = imagepath.getReadableDatabase();
			Cursor cursor = db.query(table, ThemeID, null, null,
					null, null, null);
			startManagingCursor(cursor);
			return cursor;
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
		String[] dizi={"Tarayıcı", "Sosyal Aglar","Youtube","Gmail","Galeri","Oyunlar", "Wikipedia", "İndirilenler","Ayarlar"};
		Toast.makeText(getApplicationContext(), ""+dizi[position], Toast.LENGTH_LONG).show();
		
			position++;
		    CoverFlowClickableActivity.gelenposition=position;
		    alinanposition=position;
			switch (position){
			
				case 1:startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.google.com")));
				break;
				
				case 2:Intent viewIntent2 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				startActivity(viewIntent2);break;
				
				case 3:Intent viewIntent3 = new Intent("android.intent.action.VIEW", Uri.parse("http://www.youtube.com"));				
				startActivity(viewIntent3);break;
				
				case 4:startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://mail.google.com/mail/")));
			   	break;
				
				case 5:Intent viewIntent5 = new Intent(getApplicationContext(), SubMenuActivity.class);				
				startActivity(viewIntent5);break;
	
				case 6:Intent viewIntent6 = new Intent(getApplicationContext(), GameSubMenu.class);				
				startActivity(viewIntent6);break;
				
				case 7:startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://tr.wikipedia.org/wiki/Ana_Sayfa")));
			   	break;
				
				case 8:Intent viewIntent8 = new Intent(new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS));				
				startActivity(viewIntent8);break;
				
				case 9:Intent viewIntent9 = new Intent(getApplicationContext(), ThemeActivity.class);				
				startActivity(viewIntent9);break;

				
			}

	}

//	@Override
//	public void onBackPressed() {
//		temp++;
//		GridView g= (GridView) findViewById(com.comu.android.R.id.gridView);
//		g.setVisibility(View.INVISIBLE);
//		//setContentView(R.layout.main);
//		if(temp>=2) {
//		Intent MainScreen= new Intent(getApplicationContext(),CoverFlowClickableActivity.class);
//		startActivity(MainScreen);
//		temp=0;
//		}
//	
//	}
	
}
