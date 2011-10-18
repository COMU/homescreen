package org.comu.homescreen;



import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class PrepareRequestTokenActivity extends Activity {

	final String TAG=getClass().getName();
	private OAuthProvider provider;
	private OAuthConsumer consumer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	this.consumer= new CommonsHttpOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
	this.provider= new CommonsHttpOAuthProvider(Constants.REQUEST_URL, Constants.ACCESS_URL, Constants.AUTHORIZE_URL);
	new OAuthRequestTokenTask(this,provider,consumer).execute();
	
	}
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		
		SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(this);
		final Uri uri=intent.getData();
		if(uri !=null && uri.getScheme().equals(Constants.OAUTH_CALLBACK_SCHEME)){
			
			Log.i(TAG, "Callback received : " + uri);
			Log.i(TAG, "Retrieving Access Token");
			new RetrieveAccessTokenTask(this,consumer,provider,prefs).execute(uri);
			finish();
		}
		
		
	}
	
}

