package com.odev2;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class Odev2Activity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Animation scale = AnimationUtils.loadAnimation(this,
				com.odev2.R.anim.buyuyerek_gelen_ben_ekledim);

		Gallery g = (Gallery) findViewById(com.odev2.R.id.galery);
		final ImageAdapter a = new ImageAdapter(this);
		g.setAdapter(a);
		g.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				ImageView resim = (ImageView) findViewById(com.odev2.R.id.resim);
				resim.setImageResource(a.allDrawableIDs[position]);
				resim.startAnimation(scale);

			}
		});

	}

}
