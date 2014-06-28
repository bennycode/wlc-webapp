package com.welovecoding.tutorial.data.category;

import com.welovecoding.tutorial.data.base.BaseService;
import com.welovecoding.tutorial.data.base.EJBLoggerInterceptor;
import com.welovecoding.tutorial.data.monitor.MonitorInterceptor;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({EJBLoggerInterceptor.class, MonitorInterceptor.class})
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

  public Category getBySlug(String slug) {
    return repository.getBySlug(slug);
  }

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(Arrays.asList(
            com.welovecoding.tutorial.api.v1.dto.CategoryDTO.class.getName(),
            com.welovecoding.tutorial.api.v2.dto.CategoryDTO.class.getName()
    ));
  }
}
