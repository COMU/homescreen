package com.comu.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class WallPaper  extends BetterActivity{
	
	protected void onCreate(Bundle savedInstanceState) {
		// Called when the activity is first created
		super.onCreate(savedInstanceState);
		
		Integer[]  wallPapers = {R.drawable.wallpaper_blue,R.drawable.wallpaper_grey,
				R.drawable.wallpaper_blue_abstract
		};
			
		final LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
		mainLayout.setBackgroundResource(wallPapers[1]);
		
		final LinearLayout subLayout = (LinearLayout) findViewById(R.id.sub_layout);
		subLayout.setBackgroundResource(wallPapers[1]);
		
		Intent intent = new Intent(getApplicationContext(),CoverFlowClickableActivity.class);
		startActivity(intent);
	}
}
