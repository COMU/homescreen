package org.comu.homescreen;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class FoursquareActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
    EditText kullaniciAdi = (EditText) findViewById(R.id.editText2);		  
	kullaniciAdi = new EditText(FoursquareActivity.this);
  
    EditText parola = (EditText) findViewById(R.id.editText1);
    parola = new EditText(FoursquareActivity.this);
 
 
    
   }

}
