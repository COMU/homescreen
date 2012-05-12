package com.comu.android;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoWatch extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.watchvideo); 
		final TextView videoTitle = (TextView) this.findViewById(R.id.videoTitle);
		final VideoView videoW = (VideoView) this.findViewById(R.id.videoView);
		
		
		
	}
	

}
