package org.comu.homescreen;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

public class MyapplicationTwitActivity extends Activity {
    private SharedPreferences prefs;
    private final Handler mTwitterHandler = new Handler();
    private TextView loginStatus;
	
    final Runnable mUpdateTwitterNotification = new Runnable() {
		
		@Override
		public void run() {
			
			Toast.makeText(getBaseContext(), "Tweet sent !", Toast.LENGTH_LONG).show();
			
			
		}
	};
    
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.prefs=PreferenceManager.getDefaultSharedPreferences(this);
        
        
        
    }
}