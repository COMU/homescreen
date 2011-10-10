package com.example.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class AndroidUygulamasiActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button buton = new Button(this);
        buton.setText("basiniz");
        setContentView(buton);
    }
}