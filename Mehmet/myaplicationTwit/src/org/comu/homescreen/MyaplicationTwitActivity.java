package org.comu.homescreen;

import twitter4j.Twitter;
import twitter4j.auth.RequestToken;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MyaplicationTwitActivity extends Activity {

	private static final String TAG = "MyaplicationTwitActivity";

	private static final String PREF_ACCESS_TOKEN = "accessToken";
	private static final String PREF_ACCESS_TOKEN_SECRET = "accessTokenSecret";
	private static final String CONSUMER_KEY = "Eb1Z7HpRThDW55MO0g0D0g";
	private static final String CONSUMER_SECRET = "mb2d14uDt5weTlRhp5il5r8lPIWc37XhNJKGEEiQ";
	private static final String CALLBACK_URL = "myaplicationTweet-android:///";
	private SharedPreferences mPrefs;
	private Twitter mTwitter;
	private RequestToken mReqToken;
	private Button mLoginButton;
	private Button mTweetButton;
	private EditText tweet;
	
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
    }
}