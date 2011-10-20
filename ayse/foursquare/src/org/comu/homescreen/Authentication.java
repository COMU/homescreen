package org.comu.homescreen;

import java.io.IOException;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;


public class Authentication {
	
	public void authenticationRequest(HttpServletRequest request, HttpServletResponse response) {
	    FoursquareApi foursquareApi = new FoursquareApi("Client ID", "Client Secret", "Callback URL");
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
