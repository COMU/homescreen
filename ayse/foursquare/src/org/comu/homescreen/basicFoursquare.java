package org.comu.homescreen;

import foursquare4j.FoursquareBasicAuthenticationImpl;
import foursquare4j.exception.FoursquareException;
import foursquare4j.type.Checkin;


public class basicFoursquare {
	
public static void main(String[] args) {
	        	   
  Foursquare foursquare = new Foursquare();
  
         try {
	           for (Checkin checkin : foursquare.checkins(null, null))
	                System.out.format("%s\n", checkin.getDisplay());
         } catch (FoursquareException e) {
        	        e.printStackTrace();
	                }
	        }
	}
