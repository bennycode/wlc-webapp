package de.fhb.controller;

import de.fhb.navigation.Pages;
import de.fhb.repository.CategoryRepository;
import com.welovecoding.web.entities.Category;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class CategoryController implements Serializable {

  @EJB
  private CategoryRepository repository;

  private static final Logger LOG = Logger.getLogger(CategoryController.class.getName());

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

  public String save() {
    // Log
    String template = "Saving item: {0}";
    LOG.log(Level.INFO, template, item.toString());
    // Save
    this.repository.save(item);
    this.item = new Category();
    // Navigate
    return Pages.ADMIN_CATEGORY;
  }

  public String delete() {
    // Log
    String template = "Deleting item: {0}";
    LOG.log(Level.INFO, template, item.toString());
    // Save
    this.repository.delete(item);
    this.item = new Category();
    // Navigate
    return Pages.ADMIN_CATEGORY;
  }
}
