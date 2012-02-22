package org.comu.homescreen;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class VeriTabaniActivity extends Activity{	
	private VeriTabani imagepath;
//	private String yol = "/home/ssezgin/workspace/VeriTabani/res/drawable-hdpi";
	
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imagepath = new VeriTabani(this);
//        final EditText yol = (EditText)findViewById(R.id.imagepath);
      
        Button verigonder = (Button)findViewById(R.id.buton);
        verigonder.setOnClickListener(new View.OnClickListener(){
        				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			try{
				ArrayList todoItems = new ArrayList();
		    	todoItems.add("sample_1.png");
	
		    	for(int i=0; i< todoItems.size();i++){
		    		KayitEkle(todoItems.get(i).toString());	
				}
				}
			finally{
				imagepath.close();
			}
			}

			private void KayitEkle(String yol) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = imagepath.getWritableDatabase();
				ContentValues veriler = new ContentValues();
				veriler.put("imagepath", yol);
				db.insertOrThrow("temacesitleri", null, veriler);
				
			}
		
        });
        
        Button temadegistir = (Button) findViewById(R.id.ikincibuton);
        temadegistir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					Cursor cursor = KayitGetir();
					KayitGoster(cursor);
				}
				finally{
					imagepath.close();
					
				}
			}
			private String [] SELECT = {"imagepath"};
			
			private Cursor KayitGetir() {
				// TODO Auto-generated method stub
				SQLiteDatabase db = imagepath.getReadableDatabase();
				Cursor cursor = db.query("temacesitleri", SELECT, null, null, null, null, null);
				startManagingCursor(cursor);
				return cursor;
			}
			
			private void KayitGoster(Cursor cursor){
				while(cursor.moveToNext()){
					String yol_adi = cursor.getString(cursor.getColumnIndex("imagepath"));
					ImageView img = (ImageView)findViewById(R.id.img);
//					Drawable d = Drawable.createFromPath(getFilesDir().getAbsolutePath() + yol_adi); 
					Bitmap bm = BitmapFactory.decodeFile(yol_adi);
					img.setImageBitmap(bm);
					
					
				}
				
				
			}
		});
	}
}

