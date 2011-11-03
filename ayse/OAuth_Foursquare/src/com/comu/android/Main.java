package com.comu.android;

import android.app.Activity;
import android.app.ProgressDialog;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.comu.android.FoursquareApp.FsqAuthListener;

public class Main extends Activity {
	private FoursquareApp mFsqApp;
	private ProgressDialog mProgress;

	public static final String CLIENT_ID = "DJFVLETH5C2YJTKLZ0TLINS2GBYEXJGWW4BWPCGHPNSJPDWI";
	public static final String CLIENT_SECRET = "YYIDRM0AUIJYWCJDZ0CA5V0FVBG3LKMLZJL4MNJEKI2WWUKR";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
       
        Button connectBtn 			= (Button) findViewById(R.id.b_connect);
      
        
        mFsqApp 		= new FoursquareApp(this, CLIENT_ID, CLIENT_SECRET);
        
   
        mProgress		= new ProgressDialog(this);
        
        mProgress.setMessage("Loading data ...");
        
        
      //  if (mFsqApp.hasAccessToken()) nameTv.setText("Connected as " + mFsqApp.getUserName());
        
        FsqAuthListener listener = new FsqAuthListener() {
        	
        	public void onSuccess() {
        		Toast.makeText(Main.this, "Connected as " + mFsqApp.getUserName(), Toast.LENGTH_SHORT).show();
        	//	nameTv.setText("Connected as " + mFsqApp.getUserName());
        	}
        	
        	public void onFail(String error) {
        		Toast.makeText(Main.this, error, Toast.LENGTH_SHORT).show();
        	}
        };
        
        mFsqApp.setListener(listener);
        
        //get access token and user name from foursquare
        connectBtn.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		mFsqApp.authorize();
        	}
        });  
        
       

    }
    
 
    
}

