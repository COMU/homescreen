package org.comu.homescreen;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;

public class RetrieveAccessTokenTask extends AsyncTask<Uri, Void, Void>  {

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
		return null;
	}

}
