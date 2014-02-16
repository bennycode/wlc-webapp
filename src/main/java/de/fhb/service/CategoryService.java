package de.fhb.service;

import de.fhb.entities.Category;
import de.fhb.logging.interceptor.EJBLoggerInterceptor;
import de.fhb.repository.CategoryRepository;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({EJBLoggerInterceptor.class})
public class CategoryService extends BaseService<Category, CategoryRepository> {

  @EJB
  private CategoryRepository repository;

  public CategoryService() {
  }

  @PostConstruct
  public void init() {
  }

  @Override
  protected CategoryRepository getRepository() {
    return repository;
  }

}
