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
    return getLatestCategory().getPlaylists();
  }

  public Category getLatestCategory() {
    if (latestCategory == null) {
      latestCategory = categoryController.getCategoryBySlug(categorySlug);
    }
    return latestCategory;
  }

  public Playlist getPlaylist(long playlistId) {
    return getService().find(playlistId);
  }

  public Playlist getPlaylistByCategoryAndSlug(long categoryId, String slug) {
    return getService().getByCategoryAndSlug(categoryId, slug);
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
