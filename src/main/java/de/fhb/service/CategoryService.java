package de.fhb.service;

import de.fhb.entities.Category;
import de.fhb.repository.CategoryRepository;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(Arrays.asList(
            de.fhb.rest.v1.dto.CategoryDTO.class.getName(),
            de.fhb.rest.v2.dto.CategoryDTO.class.getName()
    ));
  }
}
