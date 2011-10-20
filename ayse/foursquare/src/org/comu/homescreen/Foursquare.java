package org.comu.homescreen;

import java.io.IOException;

import foursquare4j.exception.FoursquareException;
import foursquare4j.oauth.FoursquareOAuthImpl;
import foursquare4j.oauth.OAuthConsumer;
import foursquare4j.type.Checkins;
import foursquare4j.type.Credentials;
import java.util.ArrayList;

public class Foursquare extends Controller {

    static final String FOURSQUARE_OAUTH_REQUEST_TOKEN = "http://foursquare.com/oauth/request_token";
    static final String FOURSQUARE_OAUTH_ACCESS_TOKEN = "http://foursquare.com/oauth/access_token";
    static final String FOURSQUARE_OAUTH_AUTHORIZE = "http://foursquare.com/oauth/authorize";
    static final String CONSUMER_KEY = "PGHS1VJAVPPYJTP5ZQR5JOEHWWNDHOUOCGWRIHFZIS3ZXZSB";
    static final String CONSUMER_SECRET = "OVBN15MAQL2X3FROAHGNGGKNMUC5T3ZBB0S2KOQX2TP05J3E";


    public static void authorize() throws OAuthMessageSignerException, OAuthNotAuthorizedException, OAuthExpectationFailedException, OAuthCommunicationException {

        oauth.signpost.OAuthProvider provider = new DefaultOAuthProvider(FOURSQUARE_OAUTH_REQUEST_TOKEN, FOURSQUARE_OAUTH_ACCESS_TOKEN,FOURSQUARE_OAUTH_AUTHORIZE);
        oauth.signpost.OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY,CONSUMER_SECRET);
        String authURL;

        authURL = provider.retrieveRequestToken(consumer,"");

        String tokenSecret = consumer.getTokenSecret();
        session.put("secret", tokenSecret);
        redirect(authURL);
    }

    
    public static void oauth()
    {

        oauth.signpost.OAuthProvider provider = new DefaultOAuthProvider(FOURSQUARE_OAUTH_REQUEST_TOKEN, FOURSQUARE_OAUTH_ACCESS_TOKEN,FOURSQUARE_OAUTH_AUTHORIZE);
        oauth.signpost.OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY,CONSUMER_SECRET);
        String secret = session.get("secret");
        String pinCode = params.get("oauth_token");
        consumer.setTokenWithSecret(pinCode, secret);
        provider.retrieveAccessToken(consumer, pinCode);

      
        String token = consumer.getToken();
        String tokenSecret = consumer.getTokenSecret();

       
        foursquare4j.type.Credentials credentials = new Credentials();
        credentials.setTokenSecret(tokenSecret);
        credentials.setAccessToken(token);

        foursquare4j.oauth.OAuthConsumer newConsumer = new OAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);

        foursquare4j.oauth.FoursquareOAuthImpl fs = new FoursquareOAuthImpl(newConsumer, credentials);

        try {
           
            Checkins checkins = fs.history("50", "");

            render(checkins);
        } catch (FoursquareException e) {
            e.printStackTrace();
        }
    }
}