package de.fhb.service;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.ServiceException;
import de.fhb.entities.Playlist;
import de.fhb.entities.Video;
import de.fhb.logging.interceptor.EJBLoggerInterceptor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

@Singleton
@Interceptors({EJBLoggerInterceptor.class})
public class YouTubeCrawlerService {

  private static final Logger LOG = Logger.getLogger(YouTubeCrawlerService.class.getName());
  private YouTubeService youTubeService;

  @EJB
  private PlaylistService playlistService;

  @EJB
  private VideoService videoService;

  @PostConstruct
  public void init() {
    youTubeService = new YouTubeService("WeLoveCodingApp");
  }

  public Playlist createPlaylistByCode(String code) {
    VideoFeed videoFeed = null;

    try {
      videoFeed = youTubeService.getFeed(getPlayListUrlById(code), VideoFeed.class);
    } catch (IOException | ServiceException ex) {
      Logger.getLogger(YouTubeCrawlerService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return mapPlaylist(videoFeed, code);
  }

  private Playlist mapPlaylist(VideoFeed ytPlaylist, String code) {
    Playlist playlist = null;

    if (ytPlaylist != null) {
      playlist = new Playlist();
      playlist.setName(ytPlaylist.getTitle().getPlainText());
      playlist.setCode(code);
      for (VideoEntry video : ytPlaylist.getEntries()) {
        playlist.getVideos().add(mapVideo(video));
      }
    }

    return playlist;
  }

  private Video mapVideo(VideoEntry ytVideo) {
    return new Video(getYouTubeId(ytVideo), ytVideo.getTitle().getPlainText(), "");
  }

  // https://gdata.youtube.com/feeds/api/playlists/B83C613AA955A350
  private URL getPlayListUrlById(String playListId) throws MalformedURLException {
    String url = String.format("https://gdata.youtube.com/feeds/api/playlists/%s", playListId);
    return new URL(url);
  }

  private String getYouTubeId(VideoEntry video) {
    String link = video.getHtmlLink().getHref();
    int begin = link.indexOf("?v=") + 3;
    int end = link.indexOf("&feature=youtube_gdata");
    return link.substring(begin, end);
  }

}
