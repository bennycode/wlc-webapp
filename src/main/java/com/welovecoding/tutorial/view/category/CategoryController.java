package com.welovecoding.tutorial.view.category;

import com.welovecoding.tutorial.data.category.Category;
import com.welovecoding.tutorial.data.category.CategoryService;
import com.welovecoding.tutorial.view.Pages;
import com.welovecoding.tutorial.view.scaffolding.GenFormBaseController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CategoryController extends GenFormBaseController<Category, CategoryService> {

  @EJB
  private CategoryService service;
  private Category category;
  private Category categoryBySlug;
  private List<Category> categoriesOrderedByName;

  @PostConstruct
  public void init() {
    setItem(new Category());
  }

  @Override
  public String edit() {
    super.edit();
    setItem(new Category());
    return Pages.ADMIN_CATEGORY;
  }

  @Override
  public String remove() {
    super.remove();
    setItem(new Category());
    return Pages.ADMIN_CATEGORY;
  }

  @Override
  public CategoryService getService() {
    return service;
  }

  private void loadCategoriesOrderedByName() {
    categoriesOrderedByName = getService().findAllOrderedByName();
  }

  public List<Category> getCategoriesOrderedByName() {
    if (categoriesOrderedByName == null) {
      loadCategoriesOrderedByName();
    }
    return categoriesOrderedByName;
  }

  private void loadCategoryById(long categoryId) {
    if (category == null) {
      category = getService().find(categoryId);
    }
  }

  public Category getCategory(long categoryId) {
    loadCategoryById(categoryId);
    return category;
  }

  private void loadCategoryBySlug(String categorySlug) {
    if (categoryBySlug == null) {
      categoryBySlug = getService().findBySlug(categorySlug);
    }
  }

  public Category getCategoryBySlug(String categorySlug) {
    loadCategoryBySlug(categorySlug);
    return categoryBySlug;
  }
}
