package org.comu.homescreen;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;


public class Foursquare {
	
	String consumerKey = "PGHS1VJAVPPYJTP5ZQR5JOEHWWNDHOUOCGWRIHFZIS3ZXZSB";
	String consumerSecret = "OVBN15MAQL2X3FROAHGNGGKNMUC5T3ZBB0S2KOQX2TP05J3E";

		  
	  public void authenticationRequest(HttpServletRequest request, HttpServletResponse response) {
		    FoursquareApi foursquareApi = new FoursquareApi(consumerKey, consumerKey, "Callback URL");
		    try {
		     
		      response.sendRedirect(foursquareApi.getAuthenticationUrl());
		    } catch (IOException e) {
		     
		    }
		  }
		  
		  public void handleCallback(HttpServletRequest request, HttpServletResponse response) {
		    
		    FoursquareApi foursquareApi = new FoursquareApi("Client ID", "Client Secret", "Callback URL");
		   
		    String code = request.getParameter("code");
		    try {
		     
		      foursquareApi.authenticateCode(code);
		     
		    } catch (FoursquareApiException e) {
		     
		    }
		  }
		  
		}
	
