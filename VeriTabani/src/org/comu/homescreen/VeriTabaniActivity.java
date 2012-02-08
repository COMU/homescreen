package org.comu.homescreen;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VeriTabaniActivity extends Activity{
	
	private VeriTabani imagepath;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imagepath = new VeriTabani(this);
        final EditText yol = (EditText)findViewById(R.id.imagepath);
        
        Button verigonder = (Button)findViewById(R.id.buton);
        verigonder.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			try{
				KayıtEkle(yol.getText().toString());	
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
				db.insertOrThrow("ogrenciisim", null, veriler);
				
			}
		});
	}
}

