package com.welovecoding.tutorial.view.youtube;

import com.google.api.client.auth.oauth2.Credential;
import com.welovecoding.tutorial.data.ConstraintViolationBagException;
import com.welovecoding.tutorial.data.playlist.PlaylistService;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.data.youtube.YouTubeService;
import com.welovecoding.tutorial.view.auth.AuthSessionBean;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class YouTubeImportWizard implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject private Conversation conversation;
  private String step;

  @Inject private AuthSessionBean userSessionBean;

  @EJB private YouTubeService youTubeService;
  @EJB private PlaylistService playlistService;

  private Credential credential;

  private String playlistId = "";
  private Playlist playlist = null;

  @PostConstruct
  void init() {
    conversation.begin();
    step = "parsePlaylist";
    credential = userSessionBean.getCredential();
  }

  public YouTubeImportWizard() {
  }

  public void parsePlaylist() {
    if (playlistId.isEmpty()) {
      playlist = null;
    } else {
      playlist = youTubeService.getPlaylist(playlistId, credential);
    }
  }

  public void assignMetaData() {
    step = "assignMetaData";
  }

  public void savePlaylist() {
    try {
      playlistService.edit(playlist);
    } catch (ConstraintViolationBagException ex) {
      Logger.getLogger(YouTubeImportWizard.class.getName()).log(Level.SEVERE, null, ex);
    }

    conversation.end();

    step = "parsePlaylist";
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

  public String getStep() {
    return step;
  }

  public void setStep(String step) {
    this.step = step;
  }
}
