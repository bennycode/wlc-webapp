package de.fhb.service;

import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.ServiceException;
import de.fhb.entities.Playlist;
import de.fhb.entities.Video;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;

@Singleton// could work as stateless bean
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

  public Playlist updatePlaylist(Playlist playlist) throws MalformedURLException, IOException, ServiceException {
    VideoFeed videoFeed = null;
    List<VideoEntry> ytVideos = new ArrayList<>();
    URL playlistURL = getPlayListUrlById(playlist.getCode());

    do {
      videoFeed = youTubeService.getFeed(playlistURL, VideoFeed.class);

      for (VideoEntry video : videoFeed.getEntries()) {
        ytVideos.add(video);
      }
      if (videoFeed.getNextLink() != null) {
        playlistURL = new URL(videoFeed.getNextLink().getHref());
      }
    } while (videoFeed.getNextLink() != null);

    playlist = mapPlaylist(videoFeed, playlist);

    playlist.getVideos().clear();
    for (VideoEntry video : ytVideos) {
      playlist.getVideos().add(mapVideo(video));
    }

    return playlist;
  }

  private Playlist mapPlaylist(VideoFeed ytPlaylist, Playlist playlist) {
    Playlist persistedPlaylist = playlistService.getPlaylistByCode(playlist.getCode());

    if (ytPlaylist != null) {
      if (persistedPlaylist == null) {
        playlist.setCreated(new Date());
      } else {
        playlist = persistedPlaylist;
        System.out.println("Found another Playlist with Code " + playlist.getCode() + " and ID " + playlist.getId());
      }
      playlist.setName(ytPlaylist.getTitle().getPlainText());
      playlist.setLastModified(new Date());

    }
    return playlist;
  }

  private Video mapVideo(VideoEntry ytVideo) {
    String videoID = getYouTubeId(ytVideo);
    Video video = videoService.getVideoByCode(videoID);

    if (video == null) {
      video = new Video();
      video.setCode(videoID);
    } else {
      System.out.println("Found another Video with Code " + video.getCode() + " and ID " + video.getId());
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
