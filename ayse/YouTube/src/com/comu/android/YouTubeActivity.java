package com.comu.android;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
	public static String search = "youtube";
	public static String videoIcon_list[];
	public static String video_Url[];
	public static String videoInf[];
    /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        YouTubeUrl url = new YouTubeUrl("https://gdata.youtube.com/feeds/api/videos");
        url.maxResults=15;
        url.q=search;
        
        VideoFeed feed= null;
        try {
			feed=HttpConnection.Connection(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        VideoSet.videoSetting(feed);
        video_Url=VideoSet.video_Url;
        videoInf=VideoSet.videoInf;
        videoIcon_list=VideoSet.videoIcon_list;
        
        final EditText searchParameter = (EditText) findViewById(R.id.searched);
        final ImageButton searchButton = (ImageButton) findViewById(R.id.search);
        final TextView login = (TextView) findViewById(R.id.login);
        final TextView upload = (TextView) findViewById(R.id.upload);
        final TextView createAcc = (TextView) findViewById(R.id.createAccount);
        ImageView youtube = (ImageView) findViewById(R.id.youtube);
        final GridView gridview = (GridView) findViewById(R.id.gridView1);
        gridview.setAdapter(new ImageAdapter(this));
        
        final String[]  listVideo ={"From YouTube","Trending","Popular","Music","Entertainment","Sports",
    			"Film & Animation","News & Politics","Comedy","People & Blogs","Science & Technology",
    			"Gaming","Howto & Style","Education","Pets & Animals","Autos & Vehicles","Travel & Events","Nonprofits & Activism"};
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.preference_category, listVideo));
        listView.setTextFilterEnabled(true);
        listView.setSelected(true);
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> view, View v, int index,
					long arg3) {
				
				if(index==0){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   									
				}if(index==1){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   									
				}if(index==2){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   									
				}if(index==3){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}if(index==4){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   									
				}if(index==5){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}if(index==6){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}
				if(index==7){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}if(index==8){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}
				if(index==9){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}if(index==10){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}
				if(index==11){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}if(index==12){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}
				if(index==13){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}if(index==14){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}if(index==15){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}if(index==16){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}if(index==17){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}if(index==18){
					Toast.makeText(getApplicationContext(), "dizi yeri"+index, Toast.LENGTH_LONG).show();	   					
				}
			}
        });
        
        
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
				search=searchParameter.getText().toString();			
			}		
			
		});
      
//        String feedUrl = "http://gdata.youtube.com/feeds/api/videos?"
//            +"q=skateboarding+dog"
//            +"&orderby=published"
//            +"&start-index=21"
//            +"&max-results=10"
//            +"&v=2";
    }   
  
  
    public final class ImageAdapter extends BaseAdapter {
    			
		private Context mContext;
		
	    public ImageAdapter(OnClickListener onClickListener) {
	        mContext = (Context) onClickListener;
	    }		
	    public ImageAdapter(Context c) {
	        mContext = c;
	    }
	    public int getCount() {	    	
	        return videoIcon_list.length;
	    } 
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	View v;
	    	if(convertView == null){
	    		LayoutInflater li = getLayoutInflater();
	    		v = li.inflate(R.layout.grid, null);
	    		ImageView iv = (ImageView)v.findViewById(R.id.videoImage);
	    		iv.setImageDrawable(loadImageFromURL(videoIcon_list[position]));
	    		TextView tv = (TextView)v.findViewById(R.id.videoName);
	    		tv.setTextSize(TypedValue.DENSITY_DEFAULT, 12);
	    		tv.setText(videoInf[position]);
	    	}
	    	else{
	    		v = convertView;
	    	}
	    	return v;
	    }
		private Drawable loadImageFromURL(String url){					
				        try	
				        {		
				        InputStream is = (InputStream) new URL(url).getContent();	
				        Drawable d = Drawable.createFromStream(is, "src");	
				        return d;		
				        }catch (Exception e) {		
				        System.out.println(e);	
				        return null;		
				        }		
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