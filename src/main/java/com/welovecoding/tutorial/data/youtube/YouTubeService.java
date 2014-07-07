package com.welovecoding.tutorial.data.youtube;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.welovecoding.tutorial.data.base.EJBLoggerInterceptor;
import com.welovecoding.tutorial.data.statistic.StatisticInterceptor;
import com.welovecoding.tutorial.data.playlist.PlaylistService;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.data.video.Video;
import com.welovecoding.tutorial.data.video.VideoService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({EJBLoggerInterceptor.class, StatisticInterceptor.class})
public class YouTubeService {

  private static final Logger LOG = Logger.getLogger(YouTubeService.class.getName());

  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

  @EJB private PlaylistService playlistService;
  @EJB private VideoService videoService;

  public Playlist getPlaylist(String playlistId, Credential credential) {
    YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("we-love-coding").build();
    YouTubeRepository repository = new YouTubeRepository(youtube);

    // Get playlist
    com.google.api.services.youtube.model.Playlist ytPlaylist = repository.getPlaylist(playlistId);
    Playlist playlist = getPlaylist(ytPlaylist.getId());
    YouTubeMapper.updatePlaylist(playlist, ytPlaylist);

    // Convert YouTube playlist items to playlist entities
    List<PlaylistItem> playlistItems = repository.getVideos(playlistId);
    List<Video> videos = new ArrayList<>(playlistItems.size());

    for (PlaylistItem playlistItem : playlistItems) {
      String videoId = playlistItem.getContentDetails().getVideoId();
      LOG.log(Level.INFO, "Fetched video ID: {0}", videoId);
      Video video = getVideo(videoId);
      YouTubeMapper.updateVideo(video, playlistItem);
      video.setPlaylist(playlist);
      videos.add(video);
    }

    // Connect videos and playlist
    playlist.setVideos(videos);

    return playlist;
  }

  private Playlist getPlaylist(String code) {
    Playlist playlist = playlistService.getDetachedPlaylistByCode(code);

    if (playlist == null) {
      playlist = new Playlist();
    }

    return playlist;
  }

  private Video getVideo(String code) {
    Video video = videoService.getDetachedVideoByCode(code);

    if (video == null) {
      video = new Video();
    }

    return video;
  }

}
