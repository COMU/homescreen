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
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class YouTubeActivity extends Activity {
	
	public static String developerKey = "AI39si5Ok3qgtySpXtuBZeQnK2fK1iSb08e1RMeTVlH6q_N5_4msavgPkaNsAtejFKt-fzzpBa7iSda66nXX2rPgxZYFzrMNIw";
	public static String clientId = "321041055608-poq8q9m16811aj397op9rcknp77fgk33.apps.googleusercontent.com";
    public static String videoInf[] = new String[20];
	public static String search = "";
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
			public void onItemClick(AdapterView<?> view, View v, int index,
					long arg3) {
		//		search = v.toString();
				if(index==1){
					search="youtube";
					try {
						Connection();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if(index==2) search="trending";
				if(index==3) search="popular";
				if(index==4) search="music";
				if(index==5) search="entertainment";
				if(index==6) search="sports";
				if(index==7) search="film";
				if(index==8) search="news";
				if(index==9) search="comedy";
				if(index==10) search="people";
				if(index==11) search="science";
				if(index==12) search="gadgets";
				if(index==13) search="howto";
				if(index==14) search="education";
				if(index==15) search="animals";
				if(index==16) search="vehicles";
				if(index==17) search="travel";
				if(index==17) search="nonprofits";

			}
        });
        EditText searchParameter = (EditText) findViewById(R.id.searched);
        ImageButton searchButton = (ImageButton) findViewById(R.id.search);
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
        searchButton.setOnClickListener(new OnClickListener() {			
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
     //   url.author = "searchstories";
        url.q = search;
        url.maxResults = 20;
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
    public class ImageAdapter extends BaseAdapter {
    	
		public final String videoList[] = YouTubeActivity.videoInf;
		private Context mContext;
	    public ImageAdapter(Context c) {
	        mContext = c;
	    }
	    public int getCount() {	    	
	        return videoList.length;
	    } 
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	View v;
	    	if(convertView == null){
	    		LayoutInflater li = getLayoutInflater();
	    		v = li.inflate(R.layout.grid, null);
	    		ImageView iv = (ImageView)v.findViewById(R.id.videoImage);
	    	//	iv.setImageResource(R.drawable.icon);
	    		TextView tv = (TextView)v.findViewById(R.id.videoName);
	    		tv.setText(videoList[position]);
	    	}
	    	else{
	    		v = convertView;
	    	}
	    	return v;
	    }

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub		
			return null;
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
}

}