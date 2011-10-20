package org.comu.homescreen;

import org.comu.homescreen1.DialogError;
import org.comu.homescreen1.Facebook;
import org.comu.homescreen1.Facebook.DialogListener;
import org.comu.homescreen1.FacebookError;
import org.comu.homescreen.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.comu.homescreen1.Facebook.DialogListener;
public class Facebok0Activity extends Activity {
    /** Called when the activity is first created. */
	   Facebook facebook = new Facebook("ga0RGNYHvNM5d0SLGQfpQWAPGJ8=");

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);	       
	    }

	    @Override
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);

	        facebook.authorizeCallback(requestCode, resultCode, data);
	    }
}