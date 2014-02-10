package de.fhb.controller;

import de.fhb.navigation.Pages;
import de.fhb.entities.Author;
import de.fhb.repository.AuthorRepository;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class AuthorController implements Serializable {

  private static final Logger LOG = Logger.getLogger(AuthorController.class.getName());

  @EJB
  private AuthorRepository repository;
  private Author item;

  @PostConstruct
  public void init() {
    this.item = new Author();
  }

  public Author getAuthor() {
    return item;
  }

  public void setAuthor(Author author) {
    this.item = author;
  }

  public String edit() {
    // Log
    String template = "Saving author: {0}";
    LOG.log(Level.INFO, template, item.getName());
    // Save
    this.repository.edit(item);
    this.item = new Author();
    // Navigate
    return Pages.ADMIN_AUTHORS;
  }

  public String delete() {
    // Log
    String template = "Deleting author: {0}";
    LOG.log(Level.INFO, template, item.getName());
    // Save
    this.repository.delete(item);
    this.item = new Author();
    // Navigate
    return Pages.ADMIN_AUTHORS;
  }
}
