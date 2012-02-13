package org.comu.homescreen;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VeriTabaniActivity extends Activity{	
	private VeriTabani imagepath;
//	private String yol = "/home/ssezgin/workspace/VeriTabani/res/drawable-hdpi";
	
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imagepath = new VeriTabani(this);
//        final EditText yol = (EditText)findViewById(R.id.imagepath);
      
        Button verigönder = (Button)findViewById(R.id.buton);
        verigönder.setOnClickListener(new View.OnClickListener(){
        				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			try{
				ArrayList todoItems = new ArrayList();
		    	todoItems.add("/home/ssezgin/workspace/VeriTabani/res/drawable-hdpi/icon.png");
		    	todoItems.add("/home/ssezgin/workspace/VeriTabani/res/drawable-ldpi/icon.png");
		    	todoItems.add("/home/ssezgin/workspace/VeriTabani/res/drawable-mdpi/icon.png");
		    	for(int i=0; i< todoItems.size();i++){
		    		KayıtEkle(todoItems.get(i).toString());	
				}
				}
			finally{
				imagepath.close();
			}
			}

			private void KayıtEkle(String yol) {
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
					Cursor cursor = KayıtGetir();
					KayıtGoster(cursor);
				}
				finally{
					imagepath.close();
					
				}
			}
			private String [] SELECT = {"imagepath"};
			
			private Cursor KayıtGetir() {
				// TODO Auto-generated method stub
				SQLiteDatabase db = imagepath.getReadableDatabase();
				Cursor cursor = db.query("temacesitleri", SELECT, null, null, null, null, null);
				startManagingCursor(cursor);
				return cursor;
			}
			
			private void KayıtGoster(Cursor cursor){
				StringBuilder builder = new StringBuilder();
				
				while(cursor.moveToNext()){
					String ad = cursor.getString(cursor.getColumnIndex("imagepath"));
					builder.append(ad);
				}
				
				TextView text = (TextView)findViewById(R.id.text);
				text.setText(builder);
			}
		});
	}
}

