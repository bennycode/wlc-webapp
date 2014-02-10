package de.fhb.service;

import de.fhb.repository.CategoryRepository;
import com.welovecoding.web.entities.Category;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CategoryService {

  @EJB
  private CategoryRepository repository;

  public CategoryService() {
  }

  public List<Category> getCategories() {
    return repository.getCategories();
  }

  public List<Category> getCategoriesOrderedByTitle() {
    return repository.getCategoriesOrderedByTitle();
  }
//  public String getUrlSlug(Category category) {
//    return Slugify.slugify(category.getTitle());
//  }
}
