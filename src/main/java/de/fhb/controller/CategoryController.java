package de.fhb.controller;

import de.fhb.navigation.Pages;
import de.fhb.entities.Category;
import de.fhb.repository.CategoryRepository;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * This needs to be generic! Rename item to entity
 *
 * @author Benny
 */
@Named
@SessionScoped
public class CategoryController extends BaseController implements Serializable {

  private static final Logger LOG = Logger.getLogger(CategoryController.class.getName());

  @EJB
  private CategoryRepository repository;
  private Category item;

  @PostConstruct
  public void init() {
    this.item = new Category();
  }

  public Category getItem() {
    return item;
  }

  public void setItem(Category item) {
    this.item = item;
  }

  /**
   * Saves or edits an item/entity
   *
   * @return
   */
  public String edit() {
    // Log
    String template = "Saving item: {0}";
    LOG.log(Level.INFO, template, item.getName());
    // Save
    this.repository.edit(item);
    this.item = new Category();
    // Navigate
    return Pages.ADMIN_CATEGORY;
  }

  public String delete() {
    // Log
    String template = "Deleting item: {0}";
    LOG.log(Level.INFO, template, item.getName());
    // Save
    this.repository.delete(item);
    this.item = new Category();
    // Navigate
    return Pages.ADMIN_CATEGORY;
  }
}
