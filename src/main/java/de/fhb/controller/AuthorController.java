package de.fhb.controller;

import de.fhb.entities.Author;
import de.fhb.navigation.Pages;
import de.fhb.service.AuthorService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AuthorController extends BaseController<Author, AuthorService> {

  @EJB
  private AuthorService service;

  @PostConstruct
  public void init() {
    this.item = new Author();
  }

  @Override
  public String edit() {
    super.edit();
    this.item = new Author();
    return Pages.ADMIN_AUTHORS;
  }

  @Override
  public String remove() {
    super.remove();
    this.item = new Author();
    return Pages.ADMIN_AUTHORS;
  }

  @Override
  public AuthorService getService() {
    return service;
  }

  public long getAuthorCount() {
    return service.getAuthorCount();
  }
}
