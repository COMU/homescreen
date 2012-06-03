package com.comu.android;



import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsDialog extends Dialog implements  android.view.View.OnClickListener{
	
	private XMPPClientActivity xmppClient;

    public SettingsDialog(XMPPClientActivity xmppClient) {
        super(xmppClient);
        this.xmppClient = xmppClient;
    }
    
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.settings);
        getWindow().setFlags(4, 4);
        setTitle("Login Page");
        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(this);
    }

    public void onClick(View v) {
        String username = getText(R.id.userid);
        String password = getText(R.id.password);

        // Create a connection
        ConnectionConfiguration connConfig =
                new ConnectionConfiguration("talk.google.com", 5222, "gmail.com");
        connConfig.setSASLAuthenticationEnabled(false);
        XMPPConnection connection = new XMPPConnection(connConfig);

        try {
        
        	            connection.connect();
        	
        	            Log.i("XMPPClient", "[SettingsDialog] Connected to " + connection.getHost());
        	
        	        } catch (XMPPException ex) {
        	
        	            Log.e("XMPPClient", "[SettingsDialog] Failed to connect to " + connection.getHost());
        	
        	            xmppClient.setConnection(null);
        	
        	       }
        	
        	        try {
        	
        	            connection.login(username, password);
        	
        	            Log.i("XMPPClient", "Logged in as " + connection.getUser());
        	
        	 
        	
        	            // Set the status to available
        	
        	            Presence presence = new Presence(Presence.Type.available);
        	
        	            connection.sendPacket(presence);
        	
        	            xmppClient.setConnection(connection);
        	            
        	            
        	        } catch (XMPPException ex) {
        	
        	            Log.e("XMPPClient", "[SettingsDialog] Failed to log in as " + username);
        	
        	            xmppClient.setConnection(null);
        	
        	        }
           dismiss();

    }
    
    private String getText(int id) {
        EditText widget = (EditText) this.findViewById(id);
        return widget.getText().toString();
    }
}