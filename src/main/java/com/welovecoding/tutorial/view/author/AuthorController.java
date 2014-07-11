package com.welovecoding.tutorial.view.author;

import com.welovecoding.tutorial.data.author.Author;
import com.welovecoding.tutorial.data.author.AuthorService;
import com.welovecoding.tutorial.view.Pages;
import com.welovecoding.tutorial.view.scaffolding.GenFormBaseController;
import java.util.List;
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
  private Integer authorCount;
  private List<Author> authorsOrderedByName;

  @PostConstruct
  public void init() {
    setItem(new Author());
  }

  @Override
  public String edit() {
    super.edit();

    setItem(new Author());
    return Pages.ADMIN_AUTHORS;
  }

  @Override
  public String remove() {
    super.remove();
    setItem(new Author());
    return Pages.ADMIN_AUTHORS;
  }

  @Override
  public AuthorService getService() {
    return service;
  }

  private void loadAuthorCount() {
    if (authorCount == null) {
      authorCount = getService().count();
    }
  }

  public long getAuthorCount() {
    loadAuthorCount();
    return authorCount;
  }

  private void loadAuthorsOrderedByName() {
    authorsOrderedByName = getService().orderByName();
  }

  public List<Author> getAuthorsOrderedByName() {
    if (authorsOrderedByName == null) {
      loadAuthorsOrderedByName();
    }
    return authorsOrderedByName;
  }
}
