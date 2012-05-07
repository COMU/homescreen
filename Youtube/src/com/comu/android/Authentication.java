package com.comu.android;


import android.app.Activity;
import android.os.Bundle;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.util.AuthenticationException;

public class Authentication extends Activity{
	
	public static String developerKey = "AI39si7MFNYSPPiZucLMHuRPA_gx9eFN6SYBQfPuC7lDaBXzUI0uDcF_JSwfHfo9sJ3Pcz2C5WBN8IS6cbqvK_daEuLvPnLD8Q";
	public static String clientId = "321041055608.apps.googleusercontent.com";
	public static String clientSecret = "RsKIrbSuGORkIEvqclvDDEZ8";
	public static String redirectUri = "http://localhost:port||urn:ietf:wg:oauth:2.0:oob";
	public static String scope = "https://gdata.youtube.com";
	public static String userName="";
	public static String password="";
	
	 /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);      
//	YouTubeService service = new YouTubeService(clientId, developer_key);

        
        
        
    }	
}
