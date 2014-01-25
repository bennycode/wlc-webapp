package de.fhb.controller;

import de.fhb.navigation.Pages;
import de.fhb.model.Author;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class AuthorController implements Serializable {

  private static final Logger LOG = Logger.getLogger(AuthorController.class.getName());

  private Author author;

  @PostConstruct
  public void init() {
    this.author = new Author();
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String save() {
    String template = "Saving author: {0}";
    LOG.log(Level.INFO, template, author.getName());
    // Save
    this.author = new Author();
    return Pages.ADMIN_INDEX;
  }
}
