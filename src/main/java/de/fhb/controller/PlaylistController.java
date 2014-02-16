package de.fhb.controller;

import de.fhb.entities.Playlist;
import de.fhb.navigation.Pages;
import de.fhb.service.PlaylistService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class PlaylistController extends BaseController<Playlist, PlaylistService> {

  @EJB
  private PlaylistService service;

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

}
