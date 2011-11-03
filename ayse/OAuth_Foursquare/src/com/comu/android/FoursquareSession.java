package com.comu.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class FoursquareSession {
	private SharedPreferences sharedPref;
	private Editor editor;

	private static final String SHARED = "Foursquare_Preferences";
	private static final String FSQ_USERNAME = "username";
	private static final String FSQ_ACCESS_TOKEN = "access_token";

	public FoursquareSession(Context context) {
		sharedPref 	  = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);

		editor 		  = sharedPref.edit();
	}

	
	public void storeAccessToken(String accessToken, String username) {
		editor.putString(FSQ_ACCESS_TOKEN, accessToken);
		editor.putString(FSQ_USERNAME, username);

		editor.commit();
	}

	/**
	 * Reset access token and user name
	 */
	public void resetAccessToken() {
		editor.putString(FSQ_ACCESS_TOKEN, null);
		editor.putString(FSQ_USERNAME, null);

		editor.commit();
	}


	public String getUsername() {
		return sharedPref.getString(FSQ_USERNAME, null);
	}


	public String getAccessToken() {
		return sharedPref.getString(FSQ_ACCESS_TOKEN, null);
	}
}