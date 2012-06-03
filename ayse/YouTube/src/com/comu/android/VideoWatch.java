package com.comu.android;


import java.io.IOException;

import android.app.Activity;
import android.app.DownloadManager.Query;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;


public class VideoWatch extends Activity {
	
	private static final String TAG = "MediaPlayer";
    public static String videoInf[] = new String[15];
    public static String videoIcon_list[] = new String[15];
    public static String video_Url[] = new String[15];
    public static String video_Id[] = new String[15];
    public static VideoView videoView;
    public static ProgressBar progressBar;
    static String videoPath;
    static String videoId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.watchvideo);		
		final TextView videoTitle = (TextView) this.findViewById(R.id.videoTitle);
		videoView = (VideoView) this.findViewById(R.id.videoView);
		progressBar = new ProgressBar(this);
		progressBar.setIndeterminate(true);
		progressBar.setVisibility(View.VISIBLE);
		progressBar.setEnabled(true);
		progressBar.setId(4);
		final ListView relatedVideo = (ListView) this.findViewById(R.id.relatedVideo);
		int index = YouTubeActivity.clickedVideo;
		getRelatedFeeds(VideoSet.video_Id[index]);
		relatedVideo.setAdapter(new ListImageAdapter(this));
		videoPath = VideoSet.video_Url[index];
		videoId = VideoSet.video_Id[index];
		videoTitle.setText(VideoSet.videoInf[index]);
	    playVideo();
		
		getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		 
		if(videoId == null){
			Log.i(this.getClass().getSimpleName(), "Unable to extract video ID from the intent.  Closing video activity.");
			finish();
		}

		
//		MediaPlayer videoPlayer = new MediaPlayer();
//		try {
//			videoPlayer.setDataSource(videoPath);
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//
//			MediaController controller = new MediaController(VideoWatch.this);
//			Log.d(TAG, " controller olusturuldu" );
//			controller.setEnabled(true);
//			controller.setAnchorView(videoW);
//			Log.d(TAG, " controller vs eklendi");
//			videoW.setMediaController(controller);
//			Log.d(TAG, " controller eklendi");
//		//	videoW.setVideoPath(videoPath);
//			Log.d(TAG, " video yolu  eklendi");
//			videoW.setVideoURI(Uri.parse(videoPath));
//			videoW.requestFocus();
//			Log.d(TAG, " video baslatıldı");
//			videoW.start();
//			Log.d(TAG, " gosterildi");
//			controller.show();
//       	} catch (Exception e) {
//           // TODO: handle exception
//       	}
				
	}
	
	private void playVideo() {
		// TODO Auto-generated method stub
		
		
		
		
	}

	public static void  getRelatedFeeds(String Id){
		 String Url = "https://gdata.youtube.com/feeds/api/videos/"+ Id +"/related"; 
		 YouTubeUrl url = new YouTubeUrl(Url);
	        url.maxResults=15;
	        url.q=null;
	        url.category=null;
	        
	        VideoFeed feed= null;
	        try {
				feed=HttpConnection.Connection(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			int i=0;
			for (Video video : feed.items) {
				
				videoInf[i]=video.title;
				videoIcon_list[i]=video.thumbnail.sqDefault;
				video_Url[i]=video.player.defaultUrl;
				video_Id[i]=video.id;
				i=i+1;
			} 
	}
//	private void PlayVideo()
//	 {
//	  try
//	       {      
//	              getWindow().setFormat(PixelFormat.TRANSLUCENT);
//	              MediaController mediaController = new MediaController(VideoWatch.this);
//	              mediaController.setAnchorView(videoView);           
//
//	               Uri video = Uri.parse("ytv://"+videoId);             
//	               videoView.setMediaController(mediaController);
//	               videoView.setVideoURI(video);
//	               videoView.requestFocus();              
//	               videoView.setOnPreparedListener(new OnPreparedListener()
//	               {
//
//	                   public void onPrepared(MediaPlayer mp)
//	                   {                  
//	                       progressDialog.dismiss();     
//	                       videoView.start();
//	                   }
//	               });           
//
//
//	            }
//	       catch(Exception e)
//	       {
//	                progressDialog.dismiss();
//	                System.out.println("Video Play Error :"+e.toString());
//	                finish();
//	       }   
//	 }
}
