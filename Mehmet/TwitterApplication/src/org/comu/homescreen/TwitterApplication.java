package org.comu.homescreen;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class TwitterApplication extends Activity {

	private static final String TAG = "TwitterApplication";

	private static final String PREF_ACCESS_TOKEN = "accessToken";
	private static final String PREF_ACCESS_TOKEN_SECRET = "accessTokenSecret";
	private static final String CONSUMER_KEY = "Eb1Z7HpRThDW55MO0g0D0g";
	private static final String CONSUMER_SECRET = "mb2d14uDt5weTlRhp5il5r8lPIWc37XhNJKGEEiQ";
	private static final String CALLBACK_URL = "tweetApplication:///";
	private SharedPreferences mPrefs;
	private Twitter mTwitter;
	private RequestToken mReqToken;
	private ListView l;
	private ListView l2;
	private ListView l3;

	private Button mLoginButton;
	private Button mTweetButton;
	private EditText tweet;
	private Button show_twet;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Loading TweetToTwitterActivity");
		setContentView(org.comu.homescreen.R.layout.twitmain);

		final TabHost tabHost = (TabHost) findViewById(org.comu.homescreen.R.id.tabHost);
		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("Tab_1");
		spec1.setContent(org.comu.homescreen.R.id.tab1);
		spec1.setIndicator("HomeTimeLine",
				getResources().getDrawable(org.comu.homescreen.R.drawable.h));

		TabSpec spec2 = tabHost.newTabSpec("Tab_2");
		spec2.setIndicator("Mentions",
				getResources().getDrawable(org.comu.homescreen.R.drawable.s));
		spec2.setContent(org.comu.homescreen.R.id.tab2);

		TabSpec spec3 = tabHost.newTabSpec("Tab_3");
		spec3.setIndicator("Tweet",
				getResources().getDrawable(org.comu.homescreen.R.drawable.t));
		spec3.setContent(org.comu.homescreen.R.id.tab3);

		TabSpec spec4 = tabHost.newTabSpec("Tab_4");
		spec4.setIndicator("Friends",
				getResources().getDrawable(org.comu.homescreen.R.drawable.f));
		spec4.setContent(org.comu.homescreen.R.id.tab4);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
		tabHost.addTab(spec4);
		tabHost.setCurrentTab(0);

		mPrefs = getSharedPreferences("twitterPrefs", MODE_PRIVATE);
		Log.i(TAG, "Got Preferences");

		mTwitter = new TwitterFactory().getInstance();
		Log.i(TAG, "Got Twitter4j");

		mTwitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		Log.i(TAG, "Inflated Twitter4j");
		l = (ListView) findViewById(org.comu.homescreen.R.id.liste_tab1);
		l2 = (ListView) findViewById(org.comu.homescreen.R.id.liste_tab2);
		l3 = (ListView) findViewById(org.comu.homescreen.R.id.liste_tab3);

		mTweetButton = (Button) findViewById(org.comu.homescreen.R.id.tweet_button);
		tweet = (EditText) findViewById(org.comu.homescreen.R.id.tweetText);

		if (mPrefs.contains(PREF_ACCESS_TOKEN)) {
			Log.i(TAG, "Repeat User");
			loginAuthorisedUser();
		} else {
			Log.i(TAG, "New User");
			loginNewUser();
		}

		try {
			List<Status> st = mTwitter.getHomeTimeline();
			ArrayList<UserRecord> users = new ArrayList<UserRecord>();

			int i = 0;
			for (Status s : st) {
				UserRecord u = new UserRecord(s.getUser().getName(),
						s.getText());
				u.str = (s.getUser().getProfileImageURL()).toString();
				users.add(u);

				i++;

			}

			l.setAdapter(new UserItemAdapter(getApplicationContext(),
					org.comu.homescreen.R.layout.listelement,
					(ArrayList<UserRecord>) users));

			// /////////********************************************

		} catch (Exception e2) {
			// TODO: handle exception
		}

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String arg0) {

				// ************
				try {

					if (tabHost.getCurrentTabTag().equals("Tab_1")) {
						List<Status> st = mTwitter.getHomeTimeline();
						ArrayList<UserRecord> users = new ArrayList<UserRecord>();

						int i = 0;
						for (Status s : st) {
							UserRecord u = new UserRecord(s.getUser().getName(),
									s.getText());
							u.str = (s.getUser().getProfileImageURL()).toString();
							users.add(u);

							i++;

						}

						l.setAdapter(new UserItemAdapter(getApplicationContext(),
								org.comu.homescreen.R.layout.listelement,
								(ArrayList<UserRecord>) users));


					}

					// *********
					if (tabHost.getCurrentTabTag().equals("Tab_2")) {

						List<Status> st1= mTwitter.getMentions();
								ArrayList<UserRecord> users = new ArrayList<UserRecord>();
								int j = 0;
								for(Status s:st1){
									UserRecord u= new UserRecord(s.getUser().getName(), s.getText());
									u.str= (s.getUser().getProfileImageURL()).toString();
									users.add(u);
									j++;
								}
								l2.setAdapter(new UserItemAdapter(getApplicationContext(), org.comu.homescreen.R.layout.listelement, (ArrayList<UserRecord>) users));
					}

				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// ////////////////*************************/////////////////////////////

				try {
					if (tabHost.getCurrentTabTag().equals("Tab_4")) {
						IDs followersIds = mTwitter.getFollowersIDs(-1);
						long [] ids = followersIds.getIDs();
						List<UserRecord> followers = new ArrayList<UserRecord>();
						for(int i = 0; i < 3; i++) {
							UserRecord u= new UserRecord(mTwitter.showUser(ids[i]).getName(),mTwitter.showUser(ids[i]).getLocation());
							u.str= mTwitter.showUser(ids[i]).getProfileBackgroundImageUrl().toString();
							followers.add(u);
						}
						l3.setAdapter(new UserItemAdapter(getApplicationContext(), org.comu.homescreen.R.layout.listelement, (ArrayList<UserRecord>) followers));
				
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// /////////////////******************///////////////////////////////

			}

		});

	}

	public void buttonTweet(View v) {
		Log.i(TAG, "Tweet Pressed");
		tweetMessage();
	}

	public void loginNewUser() {
		try {
			Log.i(TAG, "Request App Authentication");
			mReqToken = mTwitter.getOAuthRequestToken(CALLBACK_URL);

			Log.i(TAG, "Starting Webview to login to twitter");
			WebView twitterSite = new WebView(this);
			twitterSite.loadUrl(mReqToken.getAuthenticationURL());
			setContentView(twitterSite);

		} catch (TwitterException e) {
			Toast.makeText(this, "Twitter Login error, try again later",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void loginAuthorisedUser() {
		String token = mPrefs.getString(PREF_ACCESS_TOKEN, null);
		String secret = mPrefs.getString(PREF_ACCESS_TOKEN_SECRET, null);

		// Create the twitter access token from the credentials we got
		// previously
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
			AccessToken at = mTwitter.getOAuthAccessToken(mReqToken,
					oauthVerifier);
			mTwitter.setOAuthAccessToken(at);

			saveAccessToken(at);

			setContentView(org.comu.homescreen.R.layout.twitmain);

			enableTweetButton();
		} catch (TwitterException e) {
			Toast.makeText(this, "Twitter auth error x01, try again later",
					Toast.LENGTH_SHORT).show();
		}
	}

	private void enableTweetButton() {
		Log.i(TAG, "User logged in - allowing to tweet");
		mTweetButton.setEnabled(true);
	}

	private void tweetMessage() {
		try {

			String word = tweet.getText().toString();
			tweet.setText("");
			mTwitter.updateStatus(word);

			Toast.makeText(this, "Tweet Successful!", Toast.LENGTH_SHORT)
					.show();
		} catch (TwitterException e) {
			Toast.makeText(this, "Tweet error, try again later",
					Toast.LENGTH_SHORT).show();
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

	private Drawable createDrawableFromURL(String urlString) {
		Drawable image = null;
		try {
			URL url = new URL(urlString);
			InputStream is = (InputStream) url.getContent();
			image = Drawable.createFromStream(is, "src");
		} catch (MalformedURLException e) {
			// handle URL exception
			image = null;
		} catch (IOException e) {
			// handle InputStream exception
			image = null;
		}

		return image;
	}

	public class UserItemAdapter extends ArrayAdapter<UserRecord> {
		private ArrayList<UserRecord> users;

		public UserItemAdapter(Context context, int textViewResourceId,
				ArrayList<UserRecord> users) {
			super(context, textViewResourceId, users);
			this.users = users;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(org.comu.homescreen.R.layout.listelement, null);
				
			}

			UserRecord user = users.get(position);
			if (user != null) {
				TextView username = (TextView) v
						.findViewById(org.comu.homescreen.R.id.Username);
				TextView tweet = (TextView) v
						.findViewById(org.comu.homescreen.R.id.UserText);
				ImageView img = (ImageView) v
						.findViewById(org.comu.homescreen.R.id.avatar);

				if (username != null) {
					username.setText(user.username);
				}

				if (tweet != null) {
					tweet.setText(" " + user.usertext);
				}

				if (img != null) {
					img.setImageDrawable(createDrawableFromURL(user.str));

				}
			}
			return v;
		}
	}

	// imageView.setImageDrawable(createDrawableFromURL("http://savagelook.com/misc/sl_drop2.png"));

	public class UserRecord {
		public String username;
		public String usertext;
		public String str;
		public ImageView draw;

		public UserRecord(String username, String usertext) {
			this.username = username;
			this.usertext = usertext;

		}
	}

}