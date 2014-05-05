package com.welovecoding.tutorial.view.playlist;

import com.welovecoding.tutorial.view.scaffolding.GenFormBaseController;
import com.welovecoding.tutorial.view.Pages;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.data.author.AuthorService;
import com.welovecoding.tutorial.data.category.CategoryService;
import com.welovecoding.tutorial.data.playlist.PlaylistService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PlaylistController
        extends GenFormBaseController<Playlist, PlaylistService>
        implements Serializable {

  @EJB
  private PlaylistService service;

  @EJB
  private AuthorService authorService;

  @EJB
  private CategoryService categoryService;

  private Long authorId;

  private Long categoryId;

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

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

}
