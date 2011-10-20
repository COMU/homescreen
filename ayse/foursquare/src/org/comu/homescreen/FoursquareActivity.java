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
    }
  public  EditText kullaniciAdi = new EditText(FoursquareActivity.this);
  public  EditText parola = new EditText(FoursquareActivity.this);
 
  
    
}


