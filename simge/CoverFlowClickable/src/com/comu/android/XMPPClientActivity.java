package com.comu.android;

import java.util.ArrayList;
import java.util.Collection;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        setContentView(R.layout.gtalkmain);
             
//        mSendText = (EditText) this.findViewById(R.id.sendText);
        mList = (ListView) this.findViewById(R.id.listMessage);
        mFriend = (ListView) this.findViewById(R.id.friendList);
//        setListAdapter();

        
        // Dialog for getting the xmpp settings
        mDialog = new SettingsDialog(this);
        
     // Set a listener to show the settings dialog
        final Button login = (Button) this.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {       	
//            	login.setVisibility(View.GONE);
                mHandler.post(new Runnable() {
                    public void run() {
                        mDialog.show();
                    }
                });
                
            }
        });
        
        Button logout = (Button) this.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub		
				connection.disconnect();
				Intent intent = new Intent(getApplicationContext(), XMPPClientActivity.class);
				startActivity(intent);
			}
		});
    }
    
    
    /**
     * Called by Settings dialog when a connection is establised with the XMPP server
     *
     * @param connection
     */
    public void setConnection(final XMPPConnection connection) {
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
                        messages.add(fromName + "to" + "Me :"  );
                        messages.add(message.getBody());
                        // Add the incoming message to the list view
                        fHandler.post(new Runnable() {
                            public void run() {
                                setListAdapter();
                            }
                        });
                    }
                }
            }, filter); 	
            
            // send message
            
       
            Button send = (Button)findViewById(R.id.sendButton);
            send.setOnClickListener(new View.OnClickListener() {
            	
            	TextView side = (TextView)findViewById(R.id.karsitaraf);
            	String to = side.getText().toString();
            	
				public void onClick(View v) {
					// TODO Auto-generated method stub					    
					ChatManager chatManager = connection.getChatManager();
			
			        Chat newChat = chatManager.createChat(side.getText().toString(), new MessageListener() {
						
						public void processMessage(Chat chat, Message message) {
							// TODO Auto-generated method stub
				                try {
				                  
				                  Log.v("DEBUG", "Got:" + message.getBody());
				                  chat.sendMessage(message.getBody().toString());				            					              
				                } catch (XMPPException e) {
				                  Log.v("DEBUG", "Couldn't respond:" + e);
				                }
				                Log.v("DEBUG", message.toString());
						}
					});
				
			        
			        try {
			        	  
			        	  EditText message = (EditText) findViewById(R.id.send);	  
			              newChat.sendMessage(message.getText().toString());
			              messages.add("From me " + "to " + side.getText().toString());
			              messages.add(message.getText().toString());
			              mHandler.post(new Runnable() {
	                            public void run() {
	                                setListAdapter();
	                            }
	                        });
			            } catch (XMPPException e) {
			              Log.v("DEBUG", "couldn't send:" + e.toString());
			            }
		            
				}
			});
            
           
            final ListView l= (ListView) findViewById(com.comu.android.R.id.friendList);
            		
            final ArrayList<RosterEntry>listFriend = new ArrayList<RosterEntry>();
			Roster roster = connection.getRoster();
			Collection<RosterEntry> entries = roster.getEntries();
			for(RosterEntry entry : entries){
				listFriend.add(entry);				
				Log.v("DEBUG", entry.toString());
		}
	
			final String[] tmpList= new String[listFriend.size()];
			for(int m=0;m<listfriend.size();m++){
	    		tmpList[m] = listfriend.get(m).getName().toString();
	    	}
			
			l.setAdapter(new ArrayAdapter<RosterEntry>(l.getContext(), android.R.layout.simple_list_item_1, listFriend));
			
			
			l.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> av, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
							TextView kisi = (TextView) findViewById(R.id.karsitaraf);
							RosterEntry friend = (RosterEntry)l.getItemAtPosition(arg2);
							kisi.setText(friend.getUser().toString());
					
				}
			});
					
        }
        else{
        	Toast.makeText(getApplicationContext(), "baglantiyi kapatmistiniz", Toast.LENGTH_SHORT);
        }
    }
    

    private void setListAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.multi_line_list_item,
                messages);
        mList.setAdapter(adapter);
    }
 
		}
