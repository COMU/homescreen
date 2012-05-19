package com.comu.android;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;

import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.googleapis.json.JsonCParser;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class YouTubeActivity extends Activity {
	
	public static String developerKey = "AI39si5Ok3qgtySpXtuBZeQnK2fK1iSb08e1RMeTVlH6q_N5_4msavgPkaNsAtejFKt-fzzpBa7iSda66nXX2rPgxZYFzrMNIw";
	public static String clientId = "321041055608-poq8q9m16811aj397op9rcknp77fgk33.apps.googleusercontent.com";	
	public static String search;
	public static int clickedVideo;
	public static ArrayList<String> autoComp;
    /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        YouTubeUrl url = new YouTubeUrl("https://gdata.youtube.com/feeds/api/videos");
        url.maxResults=20;
        url.q=search;
        url.category="Music";
        
        VideoFeed feed= null;
        try {
			feed=HttpConnection.Connection(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        VideoSet.videoSetting(feed);
        
        String query="http://suggestqueries.google.com/complete/search?hl=en&ds=yt&json=t&jsonp=callbackfunction&q=search";
     //   AutoComplete();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line, autoComp );
//        AutoCompleteTextView textView = (AutoCompleteTextView)
//                findViewById(R.id.searched);
//        textView.setAdapter(adapter);
  //      final AutoCompleteTextView searchParameter = (AutoCompleteTextView) findViewById(R.id.searched);
  //      searchParameter.setAdapter(adapter);
        final EditText searchParameter = (EditText) findViewById(R.id.searched);
        
        final ImageButton searchButton = (ImageButton) findViewById(R.id.search);
        final TextView login = (TextView) findViewById(R.id.login);
        final TextView upload = (TextView) findViewById(R.id.upload);
        final TextView createAcc = (TextView) findViewById(R.id.createAccount);
        ImageView youtube = (ImageView) findViewById(R.id.youtube);
        final GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this));
        
        final String[]  listVideo ={"From YouTube","Trending","Popular","Music","Entertainment","Sports",
    			"Film & Animation","News & Politics","Comedy","People & Blogs","Science & Technology",
    			"Gaming","Howto & Style","Education"};
        ListView listView = (ListView) findViewById(R.id.listPreferences);    
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.preference_category, listVideo));
        listView.setTextFilterEnabled(true);
      //  listView.setItemChecked(0, true);
        listView.setOnItemClickListener(new OnItemClickListener() {
          
			@Override
			public void onItemClick(AdapterView<?> view, View v, int index,
					long arg3) {
				view.getChildAt(index).setSelected(true);
				final YouTubeUrl url = new YouTubeUrl("https://gdata.youtube.com/feeds/api/videos");
				 url.maxResults=15;						
				if(index==0){					
				     url.category="YouTube";					
				}if(index==1){	
					 url.category="Trending";			
				}if(index==2){
					 url.category="Popular";									
				}if(index==3){
					 url.category="Music";		
				}if(index==4){
					 url.category="Entertainment";												
				}if(index==5){
					url.category="Sports";					   					
				}if(index==6){
					url.category="Film"; 									
				}if(index==7){
					url.category="News"; 
				}if(index==8){
					url.category="Comedy";											
				}if(index==9){
					url.category="People";	   					
				}if(index==10){
					url.category="Science";	   									
				}if(index==11){
					url.category="Gaming";	   					
				}if(index==12){
					url.category="HowTo";	  								
				}if(index==13){
					url.category="Education";		   					
				}//if(index==14){
//					url.category="Pets";		   					
//				}if(index==15){
//					url.category="Autos";	   					
//				}if(index==16){
//					url.category="Travel";						
//				}if(index==17){
//					url.category="Nonprofits";		   					
//				}				
				  VideoFeed feed= null;
			        try {
						feed=HttpConnection.Connection(url);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        VideoSet.videoSetting(feed);
			   //     ((BaseAdapter) ((GridView)findViewById(R.id.gridView)).getAdapter()).notifyDataSetChanged();
			        ((BaseAdapter)gridview.getAdapter()).notifyDataSetChanged();
			        gridview.refreshDrawableState();
			        

			}	    		     
        });
        
        gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> view, View v, int index,
					long arg3) {
				// TODO Auto-generated method stub
				clickedVideo = index;
				Intent viewIntent = new Intent(getApplicationContext(), VideoWatch.class);				
				startActivity(viewIntent);
			}
		});
        
        login.setText("Sign In");
        login.setTextSize(TypedValue.DENSITY_DEFAULT, 15);
        upload.setText("Upload");
        upload.setTextSize(TypedValue.DENSITY_DEFAULT, 15);
        createAcc.setText("Create Account");
        createAcc.setTextSize(TypedValue.DENSITY_DEFAULT, 15);
        login.setOnFocusChangeListener(new OnFocusChangeListener(){
        	
			public void onFocusChange(View v, boolean hasFocus) {
			
				if(login.hasFocus())
		  			login.setTextColor(Color.RED);
		  	    else 
		  	    	login.setTextColor(Color.BLACK);		
			}	
        });
        createAcc.setOnFocusChangeListener(new OnFocusChangeListener(){
        	
			public void onFocusChange(View v, boolean hasFocus) {
				
				if(createAcc.hasFocus())
					createAcc.setTextColor(Color.RED);
		  	    else 
		  	    	createAcc.setTextColor(Color.BLACK);		
			}	
        });
        upload.setOnFocusChangeListener(new OnFocusChangeListener(){
	
        	public void onFocusChange(View v, boolean hasFocus) {
        		
        		if(upload.hasFocus())
        			upload.setTextColor(Color.RED);
        		else 
        			upload.setTextColor(Color.BLACK);		
        	}	
        });
        
        youtube.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// this return to home page.
				Intent viewIntent = new Intent(getApplicationContext(), YouTubeActivity.class);				
				startActivity(viewIntent);	
			}
		});
        login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// this go to authentication 	
				Intent viewIntent = new Intent(getApplicationContext(), Authentication.class);				
				startActivity(viewIntent);
			}	
			});
        upload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// this go to authentication 	
			
			}	
			});
        createAcc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// this go to authentication 	
				
			}	
			});
        searchButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				search=searchParameter.getText().toString();
				YouTubeUrl url = new YouTubeUrl("https://gdata.youtube.com/feeds/api/videos");
		        url.maxResults=20;
		        url.q=search;
		        url.category=null;
		        VideoFeed feed= null;
		        try {
					feed=HttpConnection.Connection(url);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        VideoSet.videoSetting(feed);
		        ((BaseAdapter)gridview.getAdapter()).notifyDataSetChanged();
				
			}		
			
		});
      
    } 
    
    public static void AutoComplete(){
  	  
  	  HttpPost request = new HttpPost("http://suggestqueries.google.com/complete/search?hl=en&ds=yt&json=t&jsonp=callbackfunction&q=search");
  	  request.setEntity((HttpEntity) new ArrayList<String>());
 
    }
  
}