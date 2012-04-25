package com.comu.android;



import android.app.Activity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ActivityWebView extends Activity {

    private static final String TAG = "ActivityWebView";
    public static final String CALLBACK_URL = "http://127.0.0.1:8000";
    public static final String CLIENT_ID = "DJFVLETH5C2YJTKLZ0TLINS2GBYEXJGWW4BWPCGHPNSJPDWI";
    public static final String CLIENT_SECRET= "QP1KQNXD3B1EZ2WTRB3BQJSIBXGE1CG3RJ1QZ13V4UWV2B32";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        
        String url =
            "https://foursquare.com/oauth2/authenticate" + 
                "?client_id=" + CLIENT_ID + 
               // "?client_secret" + CLIENT_SECRET+
                "&response_type=token" + 
                "&redirect_uri=" + CALLBACK_URL;
        
       
        WebView webview = (WebView)findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                String fragment = "#access_token=";
                int start = url.indexOf(fragment);
                if (start > -1) {
                    
                    String accessToken = url.substring(start + fragment.length(), url.length());
        			
                    Log.v(TAG, "OAuth complete, token: [" + accessToken + "].");
                	
                    Toast.makeText(ActivityWebView.this, "Token: " + accessToken, Toast.LENGTH_SHORT).show();
                }
            }
        });
        webview.loadUrl(url);
    }
}
