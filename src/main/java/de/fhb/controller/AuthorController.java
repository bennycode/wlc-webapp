package de.fhb.controller;

import de.fhb.entities.Author;
import de.fhb.navigation.Pages;
import de.fhb.service.AuthorService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class AuthorController extends BaseController<Author, AuthorService> {

  @EJB
  private AuthorService authorService;

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
    return authorService;
  }

  public long getAuthorCount() {
    return authorService.getAuthorCount();
  }
}
