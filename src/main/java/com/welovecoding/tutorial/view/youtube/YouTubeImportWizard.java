package com.welovecoding.tutorial.view.youtube;

import com.google.api.client.auth.oauth2.Credential;
import com.welovecoding.tutorial.data.ConstraintViolationBagException;
import com.welovecoding.tutorial.data.author.Author;
import com.welovecoding.tutorial.data.category.Category;
import com.welovecoding.tutorial.data.playlist.PlaylistService;
import com.welovecoding.tutorial.data.playlist.entity.Difficulty;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.data.youtube.YouTubeService;
import com.welovecoding.tutorial.view.auth.AuthSessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.component.UISelectItems;
import javax.faces.model.SelectItem;
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
  private SelectItem[] difficulties;

  @PostConstruct
  void init() {
    conversation.begin();
    step = "parsePlaylist";
    credential = userSessionBean.getCredential();
  }

  public YouTubeImportWizard() {
  }

  public SelectItem[] getDifficulties() {
    Difficulty[] enumConstants = Difficulty.class.getEnumConstants();
    SelectItem[] items = new SelectItem[enumConstants.length];

    for (int i = 0; i < enumConstants.length; i++) {
      Difficulty enumConstant = enumConstants[i];
      items[i] = new SelectItem(enumConstant, enumConstant.toString());
    }

    return items;
  }

  public void setDifficulties(SelectItem[] difficulties) {
    this.difficulties = difficulties;
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
