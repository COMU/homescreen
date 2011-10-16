package com.comu.homescreen;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TwitterActivity extends Activity {
    

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        EditText username=(EditText)findViewById(com.comu.homescreen.R.id.UserName);
        EditText pwd=(EditText)findViewById(com.comu.homescreen.R.id.passwd);
        String Name=username.getText().toString();
        String Pwd=pwd.getText().toString();
      Button submit =(Button)findViewById(com.comu.homescreen.R.id.submit);
      
        
     final Twitter myTwit = new Twitter(Name,Pwd);
   
submit.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		
		
		 try
	     {
	     
	     myTwit.setStatus("This is my first Tweet from Android ");
	     Toast.makeText(TwitterActivity.this, "Buda ilk tweet olsun Android emulatorden :)", Toast.LENGTH_SHORT).show();
	     }
	     catch(TwitterException.E401 e)
	     {
	     // comes here when username or password is wrongs
	     Toast.makeText(TwitterActivity.this, " Giris Basarisiz ",Toast.LENGTH_SHORT).show();
	     }
	     catch(Exception e)
	     {
	     Toast.makeText(TwitterActivity.this, " Agda sorun var",Toast.LENGTH_SHORT).show();
	     }
	     
		
	}
});
     
     
     
    }
     
}