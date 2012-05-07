
import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.googleapis.json.JsonCParser;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.Key;


import java.io.IOException;
import java.util.List;

public class YouTubeSample {

  public static class VideoFeed {
    @Key List<Video> items;
  }

  public static class Video {
    @Key String title;
    @Key String description;
    @Key Player player;
    @Key Thumbnail thumbnail;

  }
  public static class Thumbnail{
	  @Key("sqDefault") String  sqDefault;	  	  
  }
  
  public static class Player {
    @Key("default") String defaultUrl;
  }

  public static class YouTubeUrl extends GenericUrl {
    @Key final String alt = "jsonc";
    @Key String author;
    @Key("max-results") Integer maxResults;


    YouTubeUrl(String url) {
      super(url);
    }
  }

  public static void main(String[] args) throws IOException {
    // set up the HTTP request factory
    HttpTransport transport = new NetHttpTransport();
    final JsonFactory jsonFactory = new JacksonFactory();
    HttpRequestFactory factory = transport.createRequestFactory(new HttpRequestInitializer() {

      @Override
      public void initialize(HttpRequest request) {
        // set the parser
        JsonCParser parser = new JsonCParser(jsonFactory);
        request.addParser(parser);
        // set up the Google headers
        GoogleHeaders headers = new GoogleHeaders();
        headers.setApplicationName("Google-YouTubeSample/1.0");
        headers.gdataVersion = "2"; 
        request.setHeaders(headers);
   
      }
    });
    // build the YouTube URL
    YouTubeUrl url = new YouTubeUrl("https://gdata.youtube.com/feeds/api/videos");
    url.author = "searchstories";
    url.maxResults = 3;
    // build the HTTP GET request
    HttpRequest request = factory.buildGetRequest(url);
    // execute the request and the parse video feed
    VideoFeed feed = request.execute().parseAs(VideoFeed.class);
    for (Video video : feed.items) {
      System.out.println();
      System.out.println("Video title: " + video.title);
      System.out.println("Description: " + video.description);
      System.out.println("Play URL: " + video.player.defaultUrl);
      System.out.println("Video Image: "+ video.thumbnail.sqDefault);
    }
  }
}