package com.comu.android;

import java.io.IOException;


import com.google.api.client.googleapis.auth.clientlogin.ClientLogin;
import com.google.api.client.googleapis.auth.clientlogin.ClientLoginResponseException;
import com.google.api.client.googleapis.json.GoogleJsonRpcHttpTransport;
import com.google.api.client.googleapis.json.JsonCParser;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonEncoding;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.json.rpc2.JsonRpcRequest;
import com.google.api.client.googleapis.*;
import com.google.api.client.googleapis.auth.clientlogin.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.*;
import com.google.api.client.http.*;



import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Authentication extends Activity{
	
	public static String developerKey = "AI39si7MFNYSPPiZucLMHuRPA_gx9eFN6SYBQfPuC7lDaBXzUI0uDcF_JSwfHfo9sJ3Pcz2C5WBN8IS6cbqvK_daEuLvPnLD8Q";
	public static String clientId = "321041055608-poq8q9m16811aj397op9rcknp77fgk33.apps.googleusercontent.com";
	public static String clientSecret = "RsKIrbSuGORkIEvqclvDDEZ8";
	public static String redirectUri = "urn:ietf:wg:oauth:2.0:oob";
	public static String scope = "https://gdata.youtube.com";
//	public static String formingUrl = "https://accounts.google.com/o/oauth2/auth";

	 /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
 //       GoogleCredential credential = new GoogleCredential();
        AccountManager accountManager = AccountManager.get(this);
        Account[] accounts = accountManager.getAccountsByType("com.google");
  //    	final HttpTransport transport = new NetHttpTransport();
	  	final JsonFactory jsonFactory = new JacksonFactory();
	  	JsonCParser parser = new JsonCParser(jsonFactory);
	  	final HttpTransport transport ;
        final EditText username = (EditText) findViewById(R.id.userName);
        final EditText password = (EditText) findViewById(R.id.password);
        final GenericUrl url = new GenericUrl("https://accounts.google.com/o/oauth2/auth");

        final  Button login = (Button) findViewById(R.id.login1);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
     
//        final JsonRpcRequest jrequest = new JsonRpcRequest();
//        final HttpRequest request = null;
        		
		login.setOnClickListener(new OnClickListener() {			

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ClientLogin authenticator = new ClientLogin(); 
					authenticator.username = username.getText().toString();
			        authenticator.password = password.getText().toString();
	        			                
	                // authenticate with ClientLogin
	   //             authenticator.transport = transport;
	                authenticator.serverUrl =url; 
	                try {
						authenticator.authenticate();
					} catch (ClientLoginResponseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                //   make query request
	                
				}     
	              
		 });
    }	
}
