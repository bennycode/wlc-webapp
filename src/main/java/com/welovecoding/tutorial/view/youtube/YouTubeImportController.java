package com.welovecoding.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.welovecoding.entities.Playlist;
import com.welovecoding.security.auth.UserSessionBean;
import com.welovecoding.service.YouTubeService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class YouTubeImportController implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private UserSessionBean userSessionBean;

  @EJB
  private YouTubeService youTubeService;

  private Credential credential;

  private String playlistId = "";
  private Playlist playlist = null;

  @PostConstruct
  void init() {
    credential = userSessionBean.getCredential();
  }

  public YouTubeImportController() {
  }

  public void parsePlaylist() {
    if (playlistId.isEmpty()) {
      playlist = null;
    } else {
      playlist = youTubeService.getPlaylist(playlistId, credential);
    }
  }

  public void savePlaylist() {
    System.out.println(playlist.getName());
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
