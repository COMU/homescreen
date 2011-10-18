package org.comu.homescreen;



import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class OAuthRequestTokenTask  extends AsyncTask <Void, Void ,Void>{
	final	String TAG = getClass().getName();
	private	Context context;
	private	OAuthProvider provider;
	private	OAuthConsumer consumer;

	
	//ilkleme
	
	public OAuthRequestTokenTask(Context context,OAuthProvider provider,OAuthConsumer consumer){
		this.context=context;
		this.provider=provider;
		this.consumer=consumer;
	}
	//extend ettigimizde default implement edilen doInBackground metodu
	
	@Override
	protected Void doInBackground(Void... params) {
		try {
			
		Log.i(TAG,"  request token from google ");	
		final String url = provider.retrieveRequestToken(consumer, Constants.OAUTH_CALLBACK_URL);
		Log.i(TAG, "Browser using  to the authorize URL : " + url);
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_FROM_BACKGROUND);
		context.startActivity(intent);	
			
		} catch (Exception e) {
			
			Log.e(TAG, "Error during OAUth retrieve request token", e);
			
		}
		return null;
	}	
}
