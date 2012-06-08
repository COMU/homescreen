package com.comu.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class SDCardImagesActivity extends Activity {
      /** Called when the activity is first created. */
      private Cursor imagecursor, actualimagecursor;
      private int image_column_index, actual_image_column_index;
      GridView imagegrid;
      private int count;
      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.image);
            init_phone_image_grid();
      }
      private void init_phone_image_grid() {
            String[] img = { MediaStore.Images.Thumbnails._ID };
            imagecursor = managedQuery(
MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, img, null,
null, MediaStore.Images.Thumbnails.IMAGE_ID + "");
            image_column_index = imagecursor
.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
            count = imagecursor.getCount(); 
            imagegrid = (GridView) findViewById(R.id.PhoneImageGrid);
            imagegrid.setAdapter(new ImageAdapter(getApplicationContext()));
            imagegrid.setOnItemClickListener(new OnItemClickListener() {
                  public void onItemClick(AdapterView parent, View v,
int position, long id) {
                        System.gc();
                        String[] proj = { MediaStore.Images.Media.DATA };
                        actualimagecursor = managedQuery(
MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj,
null, null, null);
                        actual_image_column_index = actualimagecursor
.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        actualimagecursor.moveToPosition(position);
                        String i = actualimagecursor.getString(actual_image_column_index);
                        System.gc();
                        Intent intent = new Intent(getApplicationContext(), ViewImage.class);
                        intent.putExtra("filename", i);
                        startActivity(intent);
                  }
            });
      }


      public class ImageAdapter extends BaseAdapter {
            private             Context mContext;
            public ImageAdapter(Context c) {
                  mContext = c;
            }
            public int getCount() {
                  return count;
            }
            public Object getItem(int position) {
                  return position;
            }
            public long getItemId(int position) {
                  return position;
            }
            public View getView(int position,View convertView,ViewGroup parent) {
                  System.gc();
                  ImageView i = new ImageView(mContext.getApplicationContext());
                  if (convertView == null) {
                        imagecursor.moveToPosition(position);
                        int id = imagecursor.getInt(image_column_index);
                        i.setImageURI(Uri.withAppendedPath(
MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, ""
+ id));
                        i.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        i.setLayoutParams(new GridView.LayoutParams(92, 92));
                  }
                  else {
                        i = (ImageView) convertView;
                  }
                  return i;
            }
      }
}