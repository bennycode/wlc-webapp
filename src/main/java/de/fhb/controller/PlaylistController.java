package de.fhb.controller;

import de.fhb.entities.Playlist;
import de.fhb.entities.Video;
import de.fhb.navigation.Pages;
import de.fhb.service.PlaylistService;
import de.fhb.service.YouTubeCrawlerService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PlaylistController extends BaseController<Playlist, PlaylistService> {

  @EJB
  private PlaylistService service;
  @EJB
  private YouTubeCrawlerService ytService;

  @Override
  public PlaylistService getService() {
    return service;
  }

  @PostConstruct
  public void init() {
    item = new Playlist();
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

  public String importPlaylist() {
    this.item = ytService.createPlaylistByCode(item.getCode());
    System.out.println("Item");
    System.out.println("Name: " + item.getName());
    System.out.println("Code: " + item.getCode());
    System.out.println("Author: " + item.getAuthor());
    System.out.println("Category: " + item.getCategory());
    for (Video video : item.getVideoList()) {
      System.out.println("Video");
      System.out.println("Title: " + video.getTitle());
      System.out.println("Code: " + video.getCode());
    }
    return Pages.ADMIN_PLAYLISTS;

  }
}
