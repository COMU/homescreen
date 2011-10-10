package com.example.sound;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ButonActivity extends Activity implements OnClickListener {
    private int i=0;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView tw = new TextView(this);
        tw.setText(R.string.hello);
        linearLayout.addView(tw);
        Button b = new Button(this);
        linearLayout.addView(b);
        setContentView(linearLayout);        
        update(b);
        b.setOnClickListener(this);
    }
    
    private void update(Button b){
    	b.setText("click Number "+i++);
    }

	@Override
	public void onClick(View b) {
		update((Button) b);
		
	}
}