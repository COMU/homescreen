package org.comu.homescreen;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TweetToTwitterActivity extends Activity {

	private static final String TAG = "Blundell.TweetToTwitterActivity";

	private static final String PREF_ACCESS_TOKEN = "accessToken";
	private static final String PREF_ACCESS_TOKEN_SECRET = "accessTokenSecret";
	private static final String CONSUMER_KEY = "Eb1Z7HpRThDW55MO0g0D0g";
	private static final String CONSUMER_SECRET = "mb2d14uDt5weTlRhp5il5r8lPIWc37XhNJKGEEiQ";
	private static final String CALLBACK_URL = "tweet-to-twitter-blundell-01-android:///";
	private SharedPreferences mPrefs;
	private Twitter mTwitter;
	private RequestToken mReqToken;

	private Button mLoginButton;
	private Button mTweetButton;
	private EditText tweet;
	private Button show_twet;
	private TextView t;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Loading TweetToTwitterActivity");
		setContentView(R.layout.main);
		
		mPrefs = getSharedPreferences("twitterPrefs", MODE_PRIVATE);
		Log.i(TAG, "Got Preferences");
		

		mTwitter = new TwitterFactory().getInstance();
		Log.i(TAG, "Got Twitter4j");
		

		mTwitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		Log.i(TAG, "Inflated Twitter4j");
		
		mLoginButton = (Button) findViewById(R.id.login_button);
		mTweetButton = (Button) findViewById(R.id.tweet_button);
		tweet=(EditText)findViewById(R.id.tweetText);
t=(TextView)findViewById(R.id.tweet_goster);
		show_twet=(Button)findViewById(R.id.show_twet);
	show_twet.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
	try {
		List <Status> st= mTwitter.getHomeTimeline();
		
		for(Status s:st){
			t.setText(s.getUser().getName()+"  :  "+s.getText()+"\n");
	
		}
		

	} catch (TwitterException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

			
			
		}
	});
	}

	

	public void buttonLogin(View v) {
		Log.i(TAG, "Login Pressed");
		if (mPrefs.contains(PREF_ACCESS_TOKEN)) {
			Log.i(TAG, "Repeat User");
			loginAuthorisedUser();
		} else {
			Log.i(TAG, "New User");
			loginNewUser();
		}
	}

	public void buttonTweet(View v) {
		Log.i(TAG, "Tweet Pressed");
		tweetMessage();
	}

	private void loginNewUser() {
		try {
			Log.i(TAG, "Request App Authentication");
			mReqToken = mTwitter.getOAuthRequestToken(CALLBACK_URL);

			Log.i(TAG, "Starting Webview to login to twitter");
			WebView twitterSite = new WebView(this);
			twitterSite.loadUrl(mReqToken.getAuthenticationURL());
			setContentView(twitterSite);

		} catch (TwitterException e) {
			Toast.makeText(this, "Twitter Login error, try again later", Toast.LENGTH_SHORT).show();
		}
	}


	
	private void loginAuthorisedUser() {
		String token = mPrefs.getString(PREF_ACCESS_TOKEN, null);
		String secret = mPrefs.getString(PREF_ACCESS_TOKEN_SECRET, null);

		// Create the twitter access token from the credentials we got previously
		AccessToken at = new AccessToken(token, secret);

		mTwitter.setOAuthAccessToken(at);
		
		Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT).show();
		
		enableTweetButton();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.i(TAG, "New Intent Arrived");
		dealWithTwitterResponse(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "Arrived at onResume");
	}
	

	private void dealWithTwitterResponse(Intent intent) {
		Uri uri = intent.getData();
		if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
			
			String oauthVerifier = uri.getQueryParameter("oauth_verifier");

			authoriseNewUser(oauthVerifier);
		}
	}


	private void authoriseNewUser(String oauthVerifier) {
		try {
			AccessToken at = mTwitter.getOAuthAccessToken(mReqToken, oauthVerifier);
			mTwitter.setOAuthAccessToken(at);

			saveAccessToken(at);

		
			setContentView(R.layout.main);
			
			enableTweetButton();
		} catch (TwitterException e) {
			Toast.makeText(this, "Twitter auth error x01, try again later", Toast.LENGTH_SHORT).show();
		}
	}

	private void enableTweetButton() {
		Log.i(TAG, "User logged in - allowing to tweet");
		mLoginButton.setEnabled(false);
		mTweetButton.setEnabled(true);
	}


	private void tweetMessage() {
		try {
			
			String word=tweet.getText().toString();
			mTwitter.updateStatus(word);

			Toast.makeText(this, "Tweet Successful!", Toast.LENGTH_SHORT).show();
		} catch (TwitterException e) {
			Toast.makeText(this, "Tweet error, try again later", Toast.LENGTH_SHORT).show();
		}
	}

	private void saveAccessToken(AccessToken at) {
		String token = at.getToken();
		String secret = at.getTokenSecret();
		Editor editor = mPrefs.edit();
		editor.putString(PREF_ACCESS_TOKEN, token);
		editor.putString(PREF_ACCESS_TOKEN_SECRET, secret);
		editor.commit();
	}
}