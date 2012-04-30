package com.comu.android;

import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.android.Facebook;

public class FbookActivity extends Activity {
	// This is a demo application ID just to get this demo up and running
    // If you modify this to work for your own app, you must use your
    // own Facebook Application ID.
    // See http://www.facebook.com/developers/createapp.php
    public static final String FB_APP_ID = "271504916222633";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (FB_APP_ID == null) {
            Builder alertBuilder = new Builder(this);
            alertBuilder.setTitle("Warning");
            alertBuilder.setMessage("A Facebook Applicaton ID must be " +
                    "specified before running this example: see App.java");
            alertBuilder.create().show();
        }
        
        // Initialize the dispatcher
        Dispatcher dispatcher = new Dispatcher(this);
        dispatcher.addHandler("login", LoginHandler.class);
        dispatcher.addHandler("stream", StreamHandler.class);
        dispatcher.addHandler("logout", LogoutHandler.class);

        // If a session already exists, render the stream page
        // immediately. Otherwise, render the login page.
        Session session = Session.restore(this);
        if (session != null) {
            dispatcher.runHandler("stream");
        } else {
            dispatcher.runHandler("login");
        }
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        Facebook fb = Session.wakeupForAuthCallback();
        fb.authorizeCallback(requestCode, resultCode, data);
    }
}