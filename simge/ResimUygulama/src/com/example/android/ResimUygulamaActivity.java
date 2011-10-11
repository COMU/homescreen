package com.example.android;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Media;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResimUygulamaActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final MediaPlayer mp = MediaPlayer.create(ResimUygulamaActivity.this, R.raw.beep);
        
        final TextView text = (TextView) findViewById(R.id.text_id);
        
        Button buton1 = (Button) findViewById(R.id.button_id);
        Button buton2 = (Button) findViewById(R.id.button);
        
        buton1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mp.start();
				text.setText("butona bastınız");
				
			}
		});
        
        buton2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent("com.example.android.EKRANIKI"));
				mp.start();
				
			}
		});
        
    }
}