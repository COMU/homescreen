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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class YouTubeActivity extends Activity {
	
	public static String developerKey = "AI39si5Ok3qgtySpXtuBZeQnK2fK1iSb08e1RMeTVlH6q_N5_4msavgPkaNsAtejFKt-fzzpBa7iSda66nXX2rPgxZYFzrMNIw";
	public static String clientId = "321041055608-poq8q9m16811aj397op9rcknp77fgk33.apps.googleusercontent.com";
    public static String videoInf[] = new String[20];
	
    /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try {
			Connection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        final String[]  listVideo ={"From YouTube","Trending","Popular","Music","Entertainment","Sports",
    			"Film & Animation","News & Politics","Comedy","People & Blogs","Science & Technology",
    			"Gaming","Howto & Style","Education","Pets & Animals","Autos & Vehicles","Travel & Events","Nonprofits & Activism"};
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.preference_category, listVideo));
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> view, View arg1, int arg2,
					long arg3) {

			}
        });

        ImageButton search = (ImageButton) findViewById(R.id.search);
        final TextView login = (TextView) findViewById(R.id.login);
        final TextView upload = (TextView) findViewById(R.id.upload);
        final TextView createAcc = (TextView) findViewById(R.id.createAccount);
        ImageView youtube = (ImageView) findViewById(R.id.youtube);
        final GridView gridview = (GridView) findViewById(R.id.gridView1);
        gridview.setAdapter(new ImageAdapter(this));
        
        gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
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
        search.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
       
        String feedUrl = "http://gdata.youtube.com/feeds/api/videos?"
            +"q=skateboarding+dog"
            +"&orderby=published"
            +"&start-index=21"
            +"&max-results=10"
            +"&v=2";
    }   
    public static void Connection() throws IOException {
        HttpTransport transport = new NetHttpTransport();
        final JsonFactory jsonFactory = new JacksonFactory();
        HttpRequestFactory factory = transport.createRequestFactory(new HttpRequestInitializer() {
        	
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
        
        
        YouTubeUrl url = new YouTubeUrl("https://gdata.youtube.com/feeds/api/videos");
        url.author = "searchstories";
        url.maxResults = 1;
        // build the HTTP GET request
        HttpRequest request = null;
		try {
			request = factory.buildGetRequest(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // execute the request and the parse video feed
		
		VideoFeed feed = null;
        try {
			feed = request.execute().parseAs(VideoFeed.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int i=0;
        for (Video video : feed.items) {
        	videoInf[i]=video.title;
        	i=i+1;
        }

   }

}