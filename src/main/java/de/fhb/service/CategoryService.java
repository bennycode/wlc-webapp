package de.fhb.service;

import de.fhb.entities.Category;
import de.fhb.repository.CategoryRepository;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CategoryService extends BaseService<Category, CategoryRepository> {

  @EJB
  private CategoryRepository repository;
  @EJB
  private OwnCacheServerService cacheService;

  public CategoryService() {
    super(Category.class);
  }

  @PostConstruct
  public void init() {
  }

  @Override
  protected CategoryRepository getRepository() {
    return repository;
  }

  @Override
  protected OwnCacheServerService getCache() {
    return cacheService;
  }

  public List<Category> orderByName() {
    return repository.orderByName();
  }
}
