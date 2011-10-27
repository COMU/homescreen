package com.notification.sample;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationBarActivity extends Activity {

	private Button button_start, button_stop;
	private int notifi_id = 17;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button_start = (Button) findViewById(R.id.button1_start);
		button_stop = (Button) findViewById(R.id.button2_stop);
		button_start.setOnClickListener(start);
		button_stop.setOnClickListener(stop);
	}

	OnClickListener stop = new OnClickListener() {

		@Override
		public void onClick(View v) {
			close_notify();

		}
	};
	OnClickListener start = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// Start tıklandiginda bir notification cikartiyor.
			create_notify();

		}

	};

	private void create_notify() {
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager notMan = (NotificationManager) getSystemService(ns);
		int icon = R.drawable.facebook_icon;
		CharSequence tickerText = "Android Notification";
		long when = java.lang.System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		Context context = getApplicationContext();
		CharSequence contentTitle = "Facebook";
		CharSequence contentText = "Ben güncellemeleri almak istiyorum :)";
		// Notification NotificationBarActivity activityde goruntulenir
		Intent notifiaction_intent = new Intent(getBaseContext(),
				NotificationBarActivity.class);
		// PendingIntent android içinde uygun bir bileşen tarafından alınmayana
		// kadar saklanılırlar.
		// Normal intentlerde böyle bir şey yok. Intenti karşılayan bir alıcı
		// olmadığında kaydedilmezler.
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notifiaction_intent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);
		// Titreşim oluşturuyor. Bunun için AndroidManifest.xml içinde izin
		// vermeniz lazım.
		notification.vibrate = new long[] { 200, 300 };
		notMan.notify(notifi_id, notification);

	}

	private void close_notify() {
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// Bu id ye ait olan notificationu kapatiyor.
		notificationManager.cancel(notifi_id);
	}

}