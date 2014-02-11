package de.fhb.service;

import de.fhb.repository.CategoryRepository;
import de.fhb.entities.Category;
import de.fhb.repository.AbstractRepository;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class CategoryService extends BaseService<Category> {

  @EJB
  private CategoryRepository repository;

  public CategoryService() {
  }

  @Override
  public void init() {
    /* TODO: Should be done by the "BaseService" */
    Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String offsetParam = requestParameterMap.get("offset");
    this.offset = (offsetParam == null) ? 0 : Integer.valueOf(offsetParam);
    this.currentPage = (this.offset / this.amount) + 1;
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
