package com.welovecoding.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.welovecoding.security.auth.UserSessionBean;
import com.welovecoding.util.YouTubeUtils;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class YouTubeImportController implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private UserSessionBean userSessionBean;

  private String playlistId = "";
  private Playlist playlist = null;

  private YouTube youtube;
  private YouTubeUtils youTubeUtils;

  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

  @PostConstruct
  void init() {
    Credential credential = userSessionBean.getCredential();
    youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("we-love-coding").build();
    youTubeUtils = new YouTubeUtils(youtube);
  }

  public YouTubeImportController() {
  }

  // TODO: playlist is null when form is submitted
  // See: http://stackoverflow.com/a/2120183/451634
  public void parsePlaylist() {
    if (playlistId.isEmpty()) {
      playlist = null;
    } else {
      playlist = youTubeUtils.getPlaylist(playlistId);
    }
  }

  public void savePlaylist() {
    System.out.println("NOP");
  }

  public String getPlaylistId() {
    return playlistId;
  }

  public void setPlaylistId(String playlistId) {
    this.playlistId = playlistId;
  }

  public Playlist getPlaylist() {
    return playlist;
  }

  public void setPlaylist(Playlist playlist) {
    this.playlist = playlist;
  }

}
