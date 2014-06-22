package com.welovecoding.tutorial.view.playlist;

import com.welovecoding.tutorial.data.category.Category;
import com.welovecoding.tutorial.data.playlist.PlaylistService;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.view.Pages;
import com.welovecoding.tutorial.view.auth.AuthSessionBean;
import com.welovecoding.tutorial.view.category.CategoryController;
import com.welovecoding.tutorial.view.scaffolding.GenFormBaseController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PlaylistController
        extends GenFormBaseController<Playlist, PlaylistService>
        implements Serializable {

  @Inject
  private AuthSessionBean userSessionBean;
  @Inject
  private CategoryController categoryController;
  @EJB
  private PlaylistService service;
  private long categoryId;

  @Override
  public PlaylistService getService() {
    return service;
  }

  @PostConstruct
  public void init() {
    item = new Playlist();
    item.setCode("");
  }

  @Override
  public String edit() {
    // Debugger.logProperties(this.item);
    if (this.item.getCreator() == null) {
      this.item.setCreator(userSessionBean.getUser());
    }

    this.item.setLastEditor(userSessionBean.getUser());

    super.edit();
    this.item = new Playlist();
    return Pages.ADMIN_PLAYLISTS;
  }

  @Override
  public String remove() {
    super.remove();
    this.item = new Playlist();
    return Pages.ADMIN_PLAYLISTS;
  }

  public List<Playlist> getPlaylists() {
    return getService().findAllInCategory(categoryId);
  }

  public Category getLatestCategory() {
    return categoryController.getCategory(categoryId);
  }

  public Playlist getPlaylist(long playlistId) {
    return getService().find(playlistId);
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

}
