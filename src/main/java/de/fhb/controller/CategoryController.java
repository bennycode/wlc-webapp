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
public class CategoryController extends BaseController<Category> implements Serializable {

  private static final Logger LOG = Logger.getLogger(CategoryController.class.getName());

  @EJB
  private CategoryRepository repository;

  @PostConstruct
  public void init() {
    super.item = new Category();
  }

  /**
   * Saves or edits an item/entity
   *
   * @return
   */
  @Override
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

  @Override
  public String remove() {
    // Log
    String template = "Deleting item: {0}";
    LOG.log(Level.INFO, template, item.getName());
    // Save
    this.repository.remove(item);
    this.item = new Category();
    // Navigate
    return Pages.ADMIN_CATEGORY;
  }
}
