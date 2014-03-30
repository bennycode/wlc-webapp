package de.fhb.service;

import de.fhb.entities.Category;
import de.fhb.logging.interceptor.EJBLoggerInterceptor;
import de.fhb.repository.CategoryRepository;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({EJBLoggerInterceptor.class})
public class CategoryService extends BaseService<Category, CategoryRepository> {

  @EJB
  private CategoryRepository repository;
  private OwnCacheServerService cacheService;

  public CategoryService() {
    super(Category.class);
    cacheService = OwnCacheServerService.getInstance();
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
