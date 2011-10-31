package com.comu.android;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityMain extends Activity{
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        Button btn = (Button)findViewById(R.id.button);
	        btn.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                Intent intent = new Intent(ActivityMain.this, ActivityWebView.class);
	                startActivity(intent);
	            }
	        });
	    }
	

}
