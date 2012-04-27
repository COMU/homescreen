package com.comu.android;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.extensions.Rating;
import com.google.gdata.data.geo.impl.GeoRssWhere;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaPlayer;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaContent;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeMediaRating;
import com.google.gdata.data.youtube.YtPublicationState;
import com.google.gdata.data.youtube.YtStatistics;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class YoutubeActivity extends Activity {
	
	public static String developerKey = "AI39si7MFNYSPPiZucLMHuRPA_gx9eFN6SYBQfPuC7lDaBXzUI0uDcF_JSwfHfo9sJ3Pcz2C5WBN8IS6cbqvK_daEuLvPnLD8Q";
	public static String clientId = "321041055608.apps.googleusercontent.com";
	public static String applicationName = "youtube";
    /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageButton search = (ImageButton) findViewById(R.id.search);
        TextView login = (TextView) findViewById(R.id.login);
        ImageView youtube = (ImageView) findViewById(R.id.youtube);
        
        youtube.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// this return to home page.
				Intent viewIntent = new Intent(getApplicationContext(), YoutubeActivity.class);				
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
        search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
        YouTubeService service = new YouTubeService(clientId, developerKey);
        try {
			service.setUserCredentials("aysegovdeli@gmail.com", "5066076008");
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        YouTubeService service = new YouTubeService(applicationName);
        
//        YouTubeService service = new YouTubeService(clientId, developer_key);
//        String feedUrl = "http://gdata.youtube.com/feeds/api/standardfeeds/most_viewed";
//     
//		VideoFeed videoFeed = null;
//		try {
//			videoFeed = service.getFeed(new URL(feedUrl), VideoFeed.class);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		printVideoFeed(videoFeed, true);
//		
//         
//    }
//
//	public static  void printVideoFeed(VideoFeed videoFeed, boolean detailed) {
//		// TODO Auto-generated method stub
//		 for(VideoEntry videoEntry : videoFeed.getEntries() ) {
//			    printVideoEntry(videoEntry, detailed);
//			  }
//		
//	}
//
//	private static void printVideoEntry(VideoEntry videoEntry, boolean detailed) {
//		// TODO Auto-generated method stub
//		System.out.println("Title: " + videoEntry.getTitle().getPlainText());
//
//		  if(videoEntry.isDraft()) {
//		    System.out.println("Video is not live");
//		    YtPublicationState pubState = videoEntry.getPublicationState();
//		    if(pubState.getState() == YtPublicationState.State.PROCESSING) {
//		      System.out.println("Video is still being processed.");
//		    }
//		    else if(pubState.getState() == YtPublicationState.State.REJECTED) {
//		      System.out.print("Video has been rejected because: ");
//		      System.out.println(pubState.getDescription());
//		      System.out.print("For help visit: ");
//		      System.out.println(pubState.getHelpUrl());
//		    }
//		    else if(pubState.getState() == YtPublicationState.State.FAILED) {
//		      System.out.print("Video failed uploading because: ");
//		      System.out.println(pubState.getDescription());
//		      System.out.print("For help visit: ");
//		      System.out.println(pubState.getHelpUrl());
//		    }
//		  }
//
//		  if(videoEntry.getEditLink() != null) {
//		    System.out.println("Video is editable by current user.");
//		  }
//
//		  if(detailed) {
//
//		    YouTubeMediaGroup mediaGroup = videoEntry.getMediaGroup();
//
//		    System.out.println("Uploaded by: " + mediaGroup.getUploader());
//
//		    System.out.println("Video ID: " + mediaGroup.getVideoId());
//		    System.out.println("Description: " + 
//		      mediaGroup.getDescription().getPlainTextContent());
//
//		    MediaPlayer mediaPlayer = mediaGroup.getPlayer();
//		    System.out.println("Web Player URL: " + mediaPlayer.getUrl());
//		    MediaKeywords keywords = mediaGroup.getKeywords();
//		    System.out.print("Keywords: ");
//		    for(String keyword : keywords.getKeywords()) {
//		      System.out.print(keyword + ",");
//		    }
//
//		    GeoRssWhere location = videoEntry.getGeoCoordinates();
//		    if(location != null) {
//		      System.out.println("Latitude: " + location.getLatitude());
//		      System.out.println("Longitude: " + location.getLongitude());
//		    }
//
//		    Rating rating = videoEntry.getRating();
//		    if(rating != null) {
//		      System.out.println("Average rating: " + rating.getAverage());
//		    }
//
//		    YtStatistics stats = videoEntry.getStatistics();
//		    if(stats != null ) {
//		      System.out.println("View count: " + stats.getViewCount());
//		    }
//		    System.out.println();
//
//		    System.out.println("\tThumbnails:");
//		    for(MediaThumbnail mediaThumbnail : mediaGroup.getThumbnails()) {
//		      System.out.println("\t\tThumbnail URL: " + mediaThumbnail.getUrl());
//		      System.out.println("\t\tThumbnail Time Index: " +
//		      mediaThumbnail.getTime());
//		      System.out.println();
//		    }
//
//		    System.out.println("\tMedia:");
//		    for(YouTubeMediaContent mediaContent : mediaGroup.getYouTubeContents()) {
//		      System.out.println("\t\tMedia Location: "+ mediaContent.getUrl());
//		      System.out.println("\t\tMedia Type: "+ mediaContent.getType());
//		      System.out.println("\t\tDuration: " + mediaContent.getDuration());
//		      System.out.println();
//		    }
//
//		    for(YouTubeMediaRating mediaRating : mediaGroup.getYouTubeRatings()) {
//		      System.out.println("Video restricted in the following countries: " +
//		        mediaRating.getCountries().toString());
//		    }
//		  }
	}
   
}