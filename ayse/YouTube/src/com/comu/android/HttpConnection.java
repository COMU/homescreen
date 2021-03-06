package com.comu.android;

import java.io.IOException;

import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.googleapis.json.JsonCParser;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

public class HttpConnection {
	
		 
	    	
	  	public  final static HttpTransport transport = new NetHttpTransport();
	  	public  final static JsonFactory jsonFactory = new JacksonFactory();
	    public  final static HttpRequestFactory factory = transport.createRequestFactory(new HttpRequestInitializer() {
	        	
				@SuppressWarnings("deprecation")
				@Override
				public void initialize(HttpRequest request) throws IOException {
					//  set the parser
					JsonCParser parser = new JsonCParser(jsonFactory);
			        request.addParser(parser);
			        // set up the Google headers
			        GoogleHeaders headers = new GoogleHeaders();
			        headers.setApplicationName("YouTube");
			        headers.gdataVersion = "2";
			        request.setHeaders(headers);
				}
			});	 
		 
	//	 YouTubeUrl url = new YouTubeUrl("https://gdata.youtube.com/feeds/api/videos");
	 public static VideoFeed Connection(YouTubeUrl url) throws IOException {
		 
		 HttpRequest request = null;
			try {
				request = factory.buildGetRequest(url);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	        // execute the request and the parse video feed
			
			VideoFeed feed = null;
	        try {
				feed = request.execute().parseAs(VideoFeed.class);
			} catch (IOException e) {
		
				e.printStackTrace();
			}
		 return feed;
	 }	 
}
