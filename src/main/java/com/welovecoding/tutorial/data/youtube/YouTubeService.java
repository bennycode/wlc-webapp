package com.welovecoding.tutorial.data.youtube;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import javax.ejb.Stateless;

@Stateless
public class YouTubeService {

  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

  public Playlist getPlaylist(String playlistId, Credential credential) {
    // Get data
    YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("we-love-coding").build();
    YouTubeRepository repository = new YouTubeRepository(youtube);
    com.google.api.services.youtube.model.Playlist ytPlaylist = repository.getPlaylist(playlistId);

    Playlist playlist = YouTubeMapper.mapPlaylist(ytPlaylist);

    // Return data
    return playlist;
  }

}
