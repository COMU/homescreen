package org.comu.homescreen;

import java.util.Date;

import oauth.signpost.OAuth;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.style.UpdateLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyapplicationTwitActivity extends Activity {
	private SharedPreferences prefs;
	private final Handler mTwitterHandler = new Handler();
	private TextView loginStatus;

	final Runnable mUpdateTwitterNotification = new Runnable() {

		@Override
		public void run() {

			Toast.makeText(getBaseContext(), "Tweet sent !", Toast.LENGTH_LONG).show();

		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.prefs = PreferenceManager.getDefaultSharedPreferences(this);

		loginStatus = (TextView) findViewById(org.comu.homescreen.R.id.login_status);
		Button tweet = (Button) findViewById(org.comu.homescreen.R.id.btn_tweet);
		Button Clear = (Button) findViewById(org.comu.homescreen.R.id.btn_clear);
		tweet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (TwitterUtils.isAuthenticated(prefs)) {

					postTweet();
				} else {
					Intent i = new Intent(getApplicationContext(),PrepareRequestTokenActivity.class);
					i.putExtra("tweet_msg", getTweetMsg());
					startActivity(i);

				}

			}

		});

		Clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clearCredentials();
				updateLoginStatus();
			}

		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		updateLoginStatus();
	}

	public void updateLoginStatus() {
		loginStatus.setText("Logged into Twitter : " + TwitterUtils.isAuthenticated(prefs));
	}

	private void clearCredentials() {
		// TODO Auto-generated method stub

		 SharedPreferences prefs =PreferenceManager.getDefaultSharedPreferences(this);
		final Editor edit = prefs.edit();
		edit.remove(OAuth.OAUTH_TOKEN);
		edit.remove(OAuth.OAUTH_TOKEN_SECRET);
		edit.commit();
	}

	private String getTweetMsg() {
		return "Bu ilk Tweet android Emulatorden :) " + new Date().toLocaleString();
	}

	private void postTweet() {
		// TODO Auto-generated method stub

		Thread t = new Thread() {
			public void run() {

				try {
					TwitterUtils.sendTweet(prefs, getTweetMsg());
					mTwitterHandler.post(mUpdateTwitterNotification);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		};
		t.start();

	}

}