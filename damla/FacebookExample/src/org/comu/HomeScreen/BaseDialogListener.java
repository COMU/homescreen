package org.comu.HomeScreen;

import org.comu.HomeScreen1.DialogError;
import org.comu.HomeScreen1.Facebook.DialogListener;
import org.comu.HomeScreen1.FacebookError;

import android.content.DialogInterface;
import android.os.Bundle;

public class BaseDialogListener implements DialogListener {

	public void onFacebookError(FacebookError e) {
        e.printStackTrace();
    }

    public void onError(DialogError e) {
        e.printStackTrace();        
    }

    public void onCancel() {        
    }

	@Override
	public void onComplete(Bundle values) {
		// TODO Auto-generated method stub
		
	}
	
}
