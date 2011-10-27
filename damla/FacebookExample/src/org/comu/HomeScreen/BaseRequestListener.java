package org.comu.HomeScreen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.comu.HomeScreen1.AsyncFacebookRunner.RequestListener;
import org.comu.HomeScreen1.FacebookError;

import android.util.Log;

public class BaseRequestListener implements RequestListener {

	public void onFacebookError(FacebookError e, final Object state) {
        Log.e("Facebook", e.getMessage());
        e.printStackTrace();
    }

    public void onFileNotFoundException(FileNotFoundException e, final Object state) {
        Log.e("Facebook", e.getMessage());
        e.printStackTrace();
    }

    public void onIOException(IOException e, final Object state) {
        Log.e("Facebook", e.getMessage());
        e.printStackTrace();
    }

    public void onMalformedURLException(MalformedURLException e, final Object state) {
        Log.e("Facebook", e.getMessage());
        e.printStackTrace();
    }

	@Override
	public void onComplete(String response, Object state) {
		// TODO Auto-generated method stub
		
	}
    
}
