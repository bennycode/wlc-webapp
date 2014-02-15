package de.fhb.controller;

import de.fhb.entities.Author;
import de.fhb.navigation.Pages;
import de.fhb.service.AuthorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class AuthorController extends BaseController<Author> {

  private static final Logger LOG = Logger.getLogger(AuthorController.class.getName());

  @EJB
  private AuthorService service;

  @PostConstruct
  public void init() {
    this.item = new Author();
  }

  @Override
  public String edit() {
    // Log
    String template = "Saving author: {0}";
    LOG.log(Level.INFO, template, item.getName());
    // Save
    this.service.edit(item);
    this.item = new Author();
    // Navigate
    return Pages.ADMIN_AUTHORS;
  }

  @Override
  public String remove() {
    // Log
    String template = "Deleting author: {0}";
    LOG.log(Level.INFO, template, item.getName());
    // Save
    this.service.remove(item);
    this.item = new Author();
    // Navigate
    return Pages.ADMIN_AUTHORS;
  }
}
