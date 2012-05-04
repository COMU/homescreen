package com.comu.android;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;


public class Authentication extends Activity{
	
	public static String developerKey = "AI39si7MFNYSPPiZucLMHuRPA_gx9eFN6SYBQfPuC7lDaBXzUI0uDcF_JSwfHfo9sJ3Pcz2C5WBN8IS6cbqvK_daEuLvPnLD8Q";
	public static String clientId = "321041055608-poq8q9m16811aj397op9rcknp77fgk33.apps.googleusercontent.com";
	public static String clientSecret = "RsKIrbSuGORkIEvqclvDDEZ8";
	public static String redirectUri = "http://localhost:port||urn:ietf:wg:oauth:2.0:oob";
	public static String scope = "https://gdata.youtube.com";
	public static String userName="";
	public static String passWord="";
	
	 /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        final EditText username = (EditText) findViewById(R.id.userName);
        final EditText password = (EditText) findViewById(R.id.password);
        
        userName=username.getText().toString();
        passWord=password.getText().toString();
              
        String  feedUrl = "https://gdata.youtube.com/feeds/api/standardfeeds/most_popular";
        
        

    }	
}
