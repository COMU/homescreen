package com.comu.android;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListImageAdapter extends BaseAdapter {
	
//	public static String videoIcon_list[] = VideoSet.videoIcon_list;
//	public static String video_Url[] = VideoSet.video_Url;
//	public static String videoInf[] = VideoSet.videoInf; 
	
	 private final Activity context;
	 
	 public ListImageAdapter(Activity c) {
	        context = c;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return VideoSet.videoIcon_list.length;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v;		    	
		v = context.getLayoutInflater().inflate(R.layout.grid, null);
		ImageView iv = (ImageView)v.findViewById(R.id.videoImage);
		iv.setImageDrawable(loadImageFromURL(VideoWatch.videoIcon_list[position]));
		TextView tv = (TextView)v.findViewById(R.id.videoName);
		tv.setTextSize(TypedValue.DENSITY_DEFAULT, 12);
		tv.setText(VideoWatch.videoInf[position]);
	return v;
}
	private Drawable loadImageFromURL(String url){					
		        try	
		        {		
		        InputStream is = (InputStream) new URL(url).getContent();	
		        Drawable d = Drawable.createFromStream(is, "src");	
		        return d;		
		        }catch (Exception e) {		
		        System.out.println(e);	
		        return null;		
		        }		

	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
