package com.welovecoding.tutorial.view.youtube;

import com.google.api.client.auth.oauth2.Credential;
import com.welovecoding.tutorial.data.ConstraintViolationBagException;
import com.welovecoding.tutorial.data.playlist.PlaylistService;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.data.youtube.YouTubeService;
import com.welovecoding.tutorial.view.auth.AuthSessionBean;
import java.io.Serializable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;

@Named
@ConversationScoped
public class YouTubeImportWizard implements Serializable {

  private static final Logger LOG = Logger.getLogger(YouTubeImportWizard.class.getName());
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

  public void addMetaData() {
    step = "addMetaData";
  }

  public void savePlaylist() {
    try {
      // TODO: When using getCreator() == null check, then we get an exception
      // while trying to save the creator if none is present.
      // It might be because of: 'CascadeType.PERSIST' for that field.
      playlist.setCreator(userSessionBean.getUser());
      playlist.setLastEditor(userSessionBean.getUser());
      playlistService.edit(playlist);
    } catch (ConstraintViolationBagException cve) {
      Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
      for (ConstraintViolation<?> constraintViolation : constraintViolations) {
        String propertyPath = constraintViolation.getPropertyPath().toString();
        String message = constraintViolation.getMessage();
        LOG.log(Level.SEVERE, "{0}: {1}", new Object[]{propertyPath, message});
      }
    } finally {
      this.playlistId = "";
      this.playlist = null;
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
