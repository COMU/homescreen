package com.comu.android;


import com.google.gdata.client.youtube.YouTubeService;

public class Authentication {
	
	public static String developer_key = "AI39si7MFNYSPPiZucLMHuRPA_gx9eFN6SYBQfPuC7lDaBXzUI0uDcF_JSwfHfo9sJ3Pcz2C5WBN8IS6cbqvK_daEuLvPnLD8Q";
	public static String clientId = "321041055608.apps.googleusercontent.com";
	public static String clientSecret = "RsKIrbSuGORkIEvqclvDDEZ8";
	public static String redirectUri = "http://localhost:port||urn:ietf:wg:oauth:2.0:oob";
	public static String scope = "https://gdata.youtube.com";
	public static String userName="";
	public static String password="";
	
	YouTubeService service = new YouTubeService(clientId, developer_key);
	
}
