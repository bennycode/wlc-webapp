package de.fhb.service;

import de.fhb.repository.CategoryRepository;
import de.fhb.entities.Category;
import de.fhb.repository.AbstractRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CategoryService extends BaseService<Category> {

  @EJB
  private CategoryRepository repository;

  public CategoryService() {
  }

  @Override
  protected AbstractRepository getRepository() {
    return repository;
  }

  public List<Category> getCategories() {
    return repository.findAll();
  }

  public List<Category> getCategoriesOrderedByTitle() {
    return repository.getCategoriesOrderedByTitle();
  }

//  public String getUrlSlug(Category category) {
//    return Slugify.slugify(category.getTitle());
//  }
}
