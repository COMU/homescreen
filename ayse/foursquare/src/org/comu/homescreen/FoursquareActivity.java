package org.comu.homescreen;

import android.app.Activity;
import android.os.Bundle;

public class FoursquareActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         
 /*   EditText kullaniciAdi = (EditText) findViewById(R.id.editText2);		  
	kullaniciAdi = new EditText(FoursquareActivity.this); 
    EditText Parola = (EditText) findViewById(R.id.editText1);
    Parola = new EditText(FoursquareActivity.this);
    final Editable kullaniciadi = kullaniciAdi.getText();	
	final Editable parolasi = Parola.getText();	    
    final Button baglanti = new Button(FoursquareActivity.this);
    final String kullanici=kullaniciadi.toString();
    final String parola= parolasi.toString();
    baglanti.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			FoursquareOAuthImpl foursquare = new FoursquareOAuthImpl(new OAuthConsumer(consumerKey, consumerSecret));
            try {
                    
            	    
                    Credentials credentials = foursquare.authentication(kullanici, parola);
                 //   store(credentials);

                    for (Checkin checkin : foursquare.checkins(null, null))
                            System.out.format("%s\n", checkin.getDisplay());
            } catch (FoursquareException e) {
                    e.printStackTrace();
            }
    }
		 
		
	});*/
  
    }
}