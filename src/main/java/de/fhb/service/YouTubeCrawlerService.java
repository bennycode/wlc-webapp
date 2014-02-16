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
import java.util.Date;
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

  public Playlist updatePlaylist(Playlist playlist) {
    VideoFeed videoFeed = null;

    try {
      videoFeed = youTubeService.getFeed(getPlayListUrlById(playlist.getCode()), VideoFeed.class);
    } catch (IOException | ServiceException ex) {
      Logger.getLogger(YouTubeCrawlerService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return mapPlaylist(videoFeed, playlist);
  }

  private Playlist mapPlaylist(VideoFeed ytPlaylist, Playlist playlist) {
    Playlist persistedPlaylist = playlistService.getPlaylistByCode(playlist.getCode());

    if (ytPlaylist != null) {
      if (persistedPlaylist == null) {
        playlist.setCreated(new Date());
      } else {
        playlist = persistedPlaylist;
      }
      playlist.setName(ytPlaylist.getTitle().getPlainText());
      playlist.setLastModified(new Date());

      for (VideoEntry video : ytPlaylist.getEntries()) {
        playlist.getVideos().add(mapVideo(video));
      }
    }

    return playlist;
  }

  private Video mapVideo(VideoEntry ytVideo) {
    String videoID = getYouTubeId(ytVideo);
    Video video = videoService.getVideoByCode(videoID);

    if (video == null) {
      video = new Video();
      video.setCode(videoID);
    }
    video.setName(ytVideo.getTitle().getPlainText());
    video.setDescription("");
    video.setCreated(new Date(ytVideo.getPublished().getValue()));
    video.setLastModified(new Date(ytVideo.getUpdated().getValue()));
    return video;
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
