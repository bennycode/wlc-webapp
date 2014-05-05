package com.welovecoding.tutorial.view.author;

import com.welovecoding.tutorial.view.Pages;
import com.welovecoding.tutorial.view.scaffolding.GenFormBaseController;
import com.welovecoding.tutorial.data.author.Author;
import com.welovecoding.tutorial.data.author.AuthorService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AuthorController
        extends GenFormBaseController<Author, AuthorService> {

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
    return service.count();
  }
}
