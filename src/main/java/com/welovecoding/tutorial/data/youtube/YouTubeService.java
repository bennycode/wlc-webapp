package com.welovecoding.tutorial.data.youtube;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.welovecoding.tutorial.data.playlist.PlaylistService;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.data.video.Video;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class YouTubeService {

  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

  @EJB
  private PlaylistService playlistService;

  public Playlist getPlaylist(String playlistId, Credential credential) {
    // Get data
    YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("we-love-coding").build();
    YouTubeRepository repository = new YouTubeRepository(youtube);
    com.google.api.services.youtube.model.Playlist ytPlaylist = repository.getPlaylist(playlistId);

    Playlist playlist = playlistService.getPlaylistByCode(ytPlaylist.getId());
    if (playlist == null) {
      playlist = new Playlist();
    }

    YouTubeMapper.updatePlaylist(playlist, ytPlaylist);

    // Convert YouTube playlist items to playlist entities
    List<PlaylistItem> playlistItems = repository.getVideos(playlistId);
    List<Video> videos = new ArrayList<>(playlistItems.size());

    // TODO: Don't forget to map the language!
    for (PlaylistItem playlistItem : playlistItems) {
      Video video = YouTubeMapper.mapVideo(playlistItem);
      video.setPlaylist(playlist);
      videos.add(video);
    }

    // Bean Validation constraint(s) violated while executing Automatic Bean Validation on callback event:'prePersist'. Please refer to embedded ConstraintViolations for details
    // TODO: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry 'Cl9AkUL49U0' for key 'CODE'
    // Note: Will the videos already be saved when we execute "setVideos"??
    // http://stackoverflow.com/questions/12902827/jpa-merge-results-in-duplicate-entry-of-foreign-entity
     playlist.setVideos(videos);
    // Return data
    return playlist;
  }

}
