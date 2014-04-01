package de.fhb.controller;

import de.fhb.entities.Category;
import de.fhb.config.Pages;
import de.fhb.service.CategoryService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CategoryController extends GenFormBaseController<Category, CategoryService> {

  @EJB
  private CategoryService service;

  @PostConstruct
  public void init() {
    super.item = new Category();
  }

  @Override
  public String edit() {
    super.edit();
    this.item = new Category();
    return Pages.ADMIN_CATEGORY;
  }

  @Override
  public String remove() {
    super.remove();
    this.item = new Category();
    return Pages.ADMIN_CATEGORY;
  }

  @Override
  public CategoryService getService() {
    return service;
  }
}
