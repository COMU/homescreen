package org.comu.homescreen;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import foursquare4j.type.Credentials;

public class KimlikDogrulama {
     
	public static final String consumerKey = "PGHS1VJAVPPYJTP5ZQR5JOEHWWNDHOUOCGWRIHFZIS3ZXZSB";
	public static final String consumerSecret = "OVBN15MAQL2X3FROAHGNGGKNMUC5T3ZBB0S2KOQX2TP05J3E";
	
	 public void fourSquareApi() throws FoursquareApiException{
  	   
 	    FoursquareApi erisim = new FoursquareApi( consumerKey, consumerSecret,"http://aysegovdeli@blogspot.com");
 	    erisim.getAuthenticationUrl();
 	    erisim.getOAuthToken();
 	    erisim.usersRequest("15100126");
 	    erisim.authenticateCode(null);	    
 	    Credentials credentials = new Credentials();
 	    credentials.setAccessToken("https://foursquare.com/oauth2/authorize");    
 	    credentials.setTokenSecret("token secret.");
    
      }
	
}
