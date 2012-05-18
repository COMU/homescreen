package com.comu.android;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;


public class VideoWatch extends Activity {

//private static final String TAG = "MediaPlayerDemo";
//private int mVideoWidth;
//private int mVideoHeight;
//private MediaPlayer mMediaPlayer;
//private SurfaceView mPreview;
//private SurfaceHolder holder;
//int index = YouTubeActivity.clickedVideo;
//String videoPath = ImageAdapter.video_Url[index];
//private String path = videoPath;
//private Bundle extras;
//private static final String MEDIA = "media";
//private static final int LOCAL_AUDIO = 1;
//private static final int STREAM_AUDIO = 2;
//private static final int RESOURCES_AUDIO = 3;
//private static final int LOCAL_VIDEO = 4;
//private static final int STREAM_VIDEO = 5;
//private boolean mIsVideoSizeKnown = false;
//private boolean mIsVideoReadyToBePlayed = false;
//
///**
//* 
//* Called when the activity is first created.
//*/
//@Override
//public void onCreate(Bundle icicle) {
//super.onCreate(icicle);
//setContentView(R.layout.videoview);
//mPreview = (SurfaceView) findViewById(R.id.surfaceView);
//holder = mPreview.getHolder();
//holder.addCallback(this);
//holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//extras = getIntent().getExtras();
//
//
//}
//
//private void playVideo(Integer Media) {
//doCleanUp();
//try {
//
//    // Create a new media player and set the listeners
//    mMediaPlayer = new MediaPlayer();
//    mMediaPlayer.setDataSource(path);
//    mMediaPlayer.setDisplay(holder);
//    mMediaPlayer.prepare();
//    mMediaPlayer.setOnBufferingUpdateListener(this);
//    mMediaPlayer.setOnCompletionListener(this);
//    mMediaPlayer.setOnPreparedListener(this);
//    mMediaPlayer.setOnVideoSizeChangedListener(this);
//    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//
//} catch (Exception e) {
//    Log.e(TAG, "error: " + e.getMessage(), e);
//}
//}
//
//public void onBufferingUpdate(MediaPlayer arg0, int percent) {
//Log.d(TAG, "onBufferingUpdate percent:" + percent);
//
//}
//
//public void onCompletion(MediaPlayer arg0) {
//Log.d(TAG, "onCompletion called");
//}
//
//public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//Log.v(TAG, "onVideoSizeChanged called");
//if (width == 0 || height == 0) {
//    Log.e(TAG, "invalid video width(" + width + ") or height(" + height + ")");
//    return;
//}
//mIsVideoSizeKnown = true;
//mVideoWidth = width;
//mVideoHeight = height;
//if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
//    startVideoPlayback();
//}
//}
//
//public void onPrepared(MediaPlayer mediaplayer) {
//Log.d(TAG, "onPrepared called");
//mIsVideoReadyToBePlayed = true;
//if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
//    startVideoPlayback();
//}
//}
//
//public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k) {
//Log.d(TAG, "surfaceChanged called");
//
//}
//
//public void surfaceDestroyed(SurfaceHolder surfaceholder) {
//Log.d(TAG, "surfaceDestroyed called");
//}
//
//
//public void surfaceCreated(SurfaceHolder holder) {
//Log.d(TAG, "surfaceCreated called");
////playVideo(extras.getInt(MEDIA));
//
//
//}
//
//@Override
//protected void onPause() {
//super.onPause();
//releaseMediaPlayer();
//doCleanUp();
//}
//
//@Override
//protected void onDestroy() {
//super.onDestroy();
//releaseMediaPlayer();
//doCleanUp();
//}
//
//private void releaseMediaPlayer() {
//if (mMediaPlayer != null) {
//    mMediaPlayer.release();
//    mMediaPlayer = null;
//}
//}
//
//private void doCleanUp() {
//mVideoWidth = 0;
//mVideoHeight = 0;
//mIsVideoReadyToBePlayed = false;
//mIsVideoSizeKnown = false;
//}
//
//private void startVideoPlayback() {
//Log.v(TAG, "startVideoPlayback");
//holder.setFixedSize(mVideoWidth, mVideoHeight);
//mMediaPlayer.start();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.watchvideo);
		
		final TextView videoTitle = (TextView) this.findViewById(R.id.videoTitle);
		final VideoView videoW = (VideoView) this.findViewById(R.id.videoView);
		int index = YouTubeActivity.clickedVideo;
		String videoPath = ImageAdapter.video_Url[index];
	//	videoW.setVideoPath(videoPath);
		videoTitle.setText(ImageAdapter.videoInf[index]);
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
		try {

			MediaController controller = new MediaController(VideoWatch.this);
			controller.setEnabled(true);
			controller.setAnchorView(videoW);
			videoW.setMediaController(controller);
			videoW.setVideoPath(videoPath);
		//	videoW.setVideoURI(Uri.parse(videoPath));
			videoW.requestFocus();
			videoW.start();
			controller.show();
       	} catch (Exception e) {
           // TODO: handle exception
       	}

		//	videoW.start();		
		
	
	}
	
}
