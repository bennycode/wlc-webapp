package de.fhb.controller;

import com.google.gdata.util.ServiceException;
import de.fhb.entities.Author;
import de.fhb.entities.Category;
import de.fhb.entities.Playlist;
import de.fhb.entities.Video;
import de.fhb.navigation.Pages;
import de.fhb.service.AuthorService;
import de.fhb.service.CategoryService;
import de.fhb.service.PlaylistService;
import de.fhb.service.YouTubeCrawlerService;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PlaylistController extends GenFormBaseController<Playlist, PlaylistService> {

  @EJB
  private PlaylistService service;

  @EJB
  private AuthorService authorService;

  @EJB
  private CategoryService categoryService;

  @EJB
  private YouTubeCrawlerService ytService;

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

  public String importPlaylist() throws IOException, MalformedURLException, ServiceException {
    Author author = authorService.find(authorId);
    Category category = categoryService.find(categoryId);

    item.setAuthor(author);
    item.setCategory(category);
    this.item = ytService.updatePlaylist(item);

    System.out.println("Item");
    System.out.println("Name: " + item.getName());
    System.out.println("Code: " + item.getCode());
    System.out.println("Author: " + item.getAuthor());
    System.out.println("Category: " + item.getCategory());

    for (Video video : item.getVideos()) {
      System.out.println("Video");
      System.out.println("Title: " + video.getName());
      System.out.println("Code: " + video.getCode());
    }

    this.edit();

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
