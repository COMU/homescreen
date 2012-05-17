package com.comu.android;

public class VideoSet {
	
	  	public static String videoInf[] = new String[15];
		public static String videoIcon_list[] = new String[15];
		public static String video_Url[] = new String[15];
		public static String video_Id[] = new String[15];
	
	public static void videoSetting(VideoFeed feed){
		int i=0;
      for (Video video : feed.items) {
      	videoInf[i]=video.title;
      	videoIcon_list[i]=video.thumbnail.sqDefault;
      	video_Url[i]=video.player.defaultUrl;
      	video_Id[i]=video.id;
      	i=i+1;
      }    

	}
}
