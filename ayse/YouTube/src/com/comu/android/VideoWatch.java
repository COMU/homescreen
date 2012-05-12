package com.comu.android;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.TextView;
import android.widget.VideoView;
import android.media.MediaPlayer;

public class VideoWatch extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.watchvideo);
		
		final TextView videoTitle = (TextView) this.findViewById(R.id.videoTitle);
		final VideoView videoW = (VideoView) this.findViewById(R.id.videoView);
		
		int index = YouTubeActivity.clickedVideo;
		String videoPath = YouTubeActivity.video_Url[index];
	//	videoW.setVideoPath(videoPath);
		videoTitle.setText(YouTubeActivity.videoInf[index]);
		MediaPlayer videoPlayer = new MediaPlayer();
		try {
			videoPlayer.setDataSource(videoPath);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//MediaController controller = new MediaController(this);
	//	videoW.setMediaController(controller);
	//	videoW.setVideoPath(videoPath);

		
		
	}
	

}
