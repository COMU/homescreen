package org.comu.homescreen;

import java.util.ArrayList;
import java.util.Collection;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class XMPPClientActivity extends Activity {
    /** Called when the activity is first created. */
	

    private ArrayList<String> messages = new ArrayList();
    private static ArrayList<RosterEntry> listfriend = new ArrayList<RosterEntry>();
    private Handler mHandler = new Handler();
    private Handler fHandler = new Handler();
    private SettingsDialog mDialog;
    private EditText mRecipient;
    private EditText mSendText;
    private ListView mList;
    private ListView mFriend;
    private XMPPConnection connection;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
             
//        mSendText = (EditText) this.findViewById(R.id.sendText);
        mList = (ListView) this.findViewById(R.id.listMessages);
        mFriend = (ListView) this.findViewById(R.id.friendList);
//        setListAdapter();
//        setFriendAdapter();
        
        // Dialog for getting the xmpp settings
        mDialog = new SettingsDialog(this);
        
     // Set a listener to show the settings dialog
        final Button login = (Button) this.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	login.setVisibility(View.GONE);
                mHandler.post(new Runnable() {
                    public void run() {
                        mDialog.show();
                    }
                });
                
            }
        });

        
        // Set a listener to send a chat text message
//        Button send = (Button) this.findViewById(R.id.send);
//        send.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                String to = mRecipient.getText().toString();
//                String text = mSendText.getText().toString();
//
//                Log.i("XMPPClient", "Sending text [" + text + "] to [" + to + "]");
//                Message msg = new Message(to, Message.Type.chat);
//                msg.setBody(text);
//                connection.sendPacket(msg);
//                messages.add(connection.getUser() + ":");
//                messages.add(text);
//                setListAdapter();
//            }
//        });
    }
    
    
    /**
     * Called by Settings dialog when a connection is establised with the XMPP server
     *
     * @param connection
     */
    public void setConnection(XMPPConnection connection) {
        this.connection = connection;
        if (connection != null) {
//        	  Add a packet listener to get messages sent to us
            PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
            connection.addPacketListener(new PacketListener() {
                public void processPacket(Packet packet) {
                    Message message = (Message) packet;
                    if (message.getBody() != null) {
                        String fromName = StringUtils.parseBareAddress(message.getFrom());
                        Log.i("XMPPClient", "Got text [" + message.getBody() + "] from [" + fromName + "]");
                        messages.add(fromName + ":");
                        messages.add(message.getBody());
                        // Add the incoming message to the list view
                        mHandler.post(new Runnable() {
                            public void run() {
                                setListAdapter();
                            }
                        });
                    }
                }
            }, filter); 	
            
           
            ListView l= (ListView) findViewById(org.comu.homescreen.R.id.friendList);
            		
            ArrayList<RosterEntry>listFriend = new ArrayList<RosterEntry>();
			Roster roster = connection.getRoster();
			Collection<RosterEntry> entries = roster.getEntries();
			for(RosterEntry entry : entries){
				listFriend.add(entry);				
				Log.v("DEBUG", entry.toString());
		}
			String[] tmpList= new String[listFriend.size()];
			for(int m=0;m<listfriend.size();m++){
	    		tmpList[m] = listfriend.get(m).getName().toString();
	    	}
			
			l.setAdapter(new ArrayAdapter<RosterEntry>(l.getContext(), android.R.layout.simple_list_item_1, listFriend));
			
        }
    }
    


    private void setListAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.multi_line_list_item,
                messages);
        mList.setAdapter(adapter);
    }
    
		}
