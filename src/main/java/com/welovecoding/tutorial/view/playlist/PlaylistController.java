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
  private String categorySlug;
  private Category latestCategory;
  private Playlist playlist;
  private Playlist playlistByCategoryAndSlug;

  @Override
  public PlaylistService getService() {
    return service;
  }

  @PostConstruct
  public void init() {
    setItem(new Playlist());
    getItem().setCode("");
  }

  @Override
  public String edit() {
    // Debugger.logProperties(this.item);
    if (getItem().getCreator() == null) {
      getItem().setCreator(userSessionBean.getUser());
    }

    getItem().setLastEditor(userSessionBean.getUser());

    super.edit();
    setItem(new Playlist());
    return Pages.ADMIN_PLAYLISTS;
  }

  @Override
  public String remove() {
    super.remove();
    setItem(new Playlist());
    return Pages.ADMIN_PLAYLISTS;
  }

  public List<Playlist> getPlaylists() {
    return getLatestCategory().getPlaylists();
  }

  public Category getLatestCategory() {
    if (latestCategory == null) {
      latestCategory = categoryController.getCategoryBySlug(categorySlug);
    }
    return latestCategory;
  }

  private void loadPlaylistById(long playlistId) {
    if (playlist == null) {
      playlist = getService().find(playlistId);
    }
  }

  public Playlist getPlaylist(long playlistId) {
    loadPlaylistById(playlistId);
    return playlist;
  }

  private void loadPlaylistByCategoryAndSlug(long categoryId, String slug) {
    if (playlistByCategoryAndSlug == null) {
      playlistByCategoryAndSlug = getService().findByCategoryAndSlug(categoryId, slug);
    }
  }

  public Playlist getPlaylistByCategoryAndSlug(long categoryId, String slug) {
    loadPlaylistByCategoryAndSlug(categoryId, slug);
    return playlistByCategoryAndSlug;
  }

  public void setLatestCategory(Category latestCategory) {
    this.latestCategory = latestCategory;
  }

  public String getCategorySlug() {
    return categorySlug;
  }

  public void setCategorySlug(String categorySlug) {
    this.categorySlug = categorySlug;
  }

}
