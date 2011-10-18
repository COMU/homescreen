package org.comu.homescreen;



import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	try {
		this.consumer= new CommonsHttpOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		this.provider= new CommonsHttpOAuthProvider(Constants.REQUEST_URL, Constants.ACCESS_URL, Constants.AUTHORIZE_URL);
		
	} catch (Exception e) {
		// TODO: handle exception
		Log.e(TAG,"Error creating consumer / provider",e);
	}
		
		Log.i(TAG,"Starting task to retrieve request token.	");
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
	


public class RetrieveAccessTokenTask extends AsyncTask<Uri, Void, Void>  {
	final String TAG = getClass().getName();
	
	
	private Context context;
	private OAuthConsumer consumer;
	private OAuthProvider provider;
	private SharedPreferences prefs;
	
	public RetrieveAccessTokenTask(Context context,OAuthConsumer consumer,OAuthProvider provider,SharedPreferences prefs) {
	
		this.consumer=consumer;
		this.context=context;
		this.provider=provider;
		this.prefs=prefs;
	}
	
	
	
	@Override
	protected Void doInBackground(Uri... params) {
		
		
		
		// TODO Auto-generated method stub
		
		final Uri uri = params[0];
		final String oauth_verifier = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);

		try {
			provider.retrieveAccessToken(consumer, oauth_verifier);

			final Editor edit = prefs.edit();
			edit.putString(OAuth.OAUTH_TOKEN, consumer.getToken());
			edit.putString(OAuth.OAUTH_TOKEN_SECRET, consumer.getTokenSecret());
			edit.commit();
			
			String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
			String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
			
			consumer.setTokenWithSecret(token, secret);
			context.startActivity(new Intent(context,MyapplicationTwitActivity.class));

			executeAfterAccessTokenRetrieval();
			
			Log.i(TAG, "OAuth - Access Token Retrieved");
			
		} catch (Exception e) {
			Log.e(TAG, "OAuth - Access Token Retrieval Error", e);
		}

		return null;

		
		
	}
	private void executeAfterAccessTokenRetrieval() {
		String msg = 	getIntent().getExtras().getString("tweet_msg");
		try {
			TwitterUtils.sendTweet(prefs, msg);
		} catch (Exception e) {
			Log.e(TAG, "OAuth - Error sending to Twitter", e);
		}
	}
}	
}

