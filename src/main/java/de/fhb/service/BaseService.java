package de.fhb.service;

import de.fhb.entities.BaseEntity;
import de.fhb.repository.AbstractRepository;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.List;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseService<T extends BaseEntity, E extends AbstractRepository<T>> {

  private final Class<T> entityClass;

  protected abstract E getRepository();

  protected abstract OwnCacheServerService getCache();

  public BaseService(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public void create(T entity) {
    invalidateRelatedCaches();
    getRepository().create(entity);
  }

  public void edit(T entity) {
    invalidateRelatedCaches();
    getRepository().edit(entity);
  }

  public void remove(T entity) {
    invalidateRelatedCaches();
    getRepository().remove(entity);
  }

  public T find(Long id) {
    return (T) getRepository().find(id);
  }

  public List<T> findAll() {
    return (List<T>) getRepository().findAll();
  }

  public List<T> findRange(int startPosition, int maxResult) {
    return (List<T>) getRepository().findRange(startPosition, maxResult);
  }

  private void invalidateRelatedCaches() {
    String dtoFullQualifiedName = "de.fhb.rest.v1.dto." + entityClass.getSimpleName() + "DTO";
    getCache().invalidateCache("javax.ws.rs.core.Response", null);
    getCache().invalidateCache(dtoFullQualifiedName, null);
    getCache().invalidateCache("java.util.List", dtoFullQualifiedName);
  }

}
