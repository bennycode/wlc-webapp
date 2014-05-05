package com.welovecoding.tutorial.view.youtube;

import com.google.api.client.auth.oauth2.Credential;
import com.welovecoding.tutorial.data.category.Category;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.data.youtube.YouTubeService;
import com.welovecoding.tutorial.view.auth.AuthSessionBean;
import com.welovecoding.tutorial.view.scaffolding.DropdownItemsConverter;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class YouTubeImportController implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private Conversation conversation;
  // TODO: Can be an enum (or string)
  private int step;

  @Inject
  private AuthSessionBean userSessionBean;

  @EJB
  private YouTubeService youTubeService;

  private Credential credential;

  private Category category;
  private String playlistId = "";
  private Playlist playlist = null;

  @PostConstruct
  void init() {
    conversation.begin();
    step = 1;
    credential = userSessionBean.getCredential();
  }

  public YouTubeImportController() {
  }

  public void parsePlaylist() {
    step += 1;

    if (playlistId.isEmpty()) {
      playlist = null;
    } else {
      playlist = youTubeService.getPlaylist(playlistId, credential);
    }
  }

  public void savePlaylist() {
    step += 1;

    System.out.println(playlist.getName());
  }

  public void saveMetaData() {
    conversation.end();
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

  public int getStep() {
    return step;
  }

  public void setStep(int step) {
    this.step = step;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

}
