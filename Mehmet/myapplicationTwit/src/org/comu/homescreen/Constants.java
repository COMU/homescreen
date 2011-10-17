package org.comu.homescreen;

public class Constants {

	public static final String CONSUMER_KEY = "IqfhClJF8BNSZNoTewHGQ";
	public static final String CONSUMER_SECRET= "BhyzYq9Ek5m9R4fLJOHqG6rCY0SQ6Xzo0EbBzrn7Rw";
	
	public static final String REQUEST_URL = "https://api.twitter.com/oauth/request_token";
	public static final String ACCESS_URL = "https://api.twitter.com/oauth/access_token";
	public static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";
	
	public static final String	OAUTH_CALLBACK_SCHEME	= "x-oauthflow-twitter";
	public static final String	OAUTH_CALLBACK_HOST		= "callback";
	public static final String	OAUTH_CALLBACK_URL		= OAUTH_CALLBACK_SCHEME + "://" + OAUTH_CALLBACK_HOST;

}

