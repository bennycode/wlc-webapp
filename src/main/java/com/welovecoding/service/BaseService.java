package com.welovecoding.service;

import com.welovecoding.config.Packages;
import com.welovecoding.controller.GenFormBaseController;
import com.welovecoding.entities.BaseEntity;
import com.welovecoding.exception.ConstraintViolationBagException;
import com.welovecoding.repository.AbstractRepository;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.MappedSuperclass;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

@MappedSuperclass
//@Interceptors({EJBLoggerInterceptor.class})
public abstract class BaseService<T extends BaseEntity, E extends AbstractRepository<T>> {

  private final Class<T> entityClass;

  protected abstract E getRepository();

  protected abstract OwnCacheServerService getCache();

  protected abstract Set<String> typesToClear();

  public BaseService(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public void create(T entity) {
    invalidateRelatedCaches();
    getRepository().create(entity);
  }

  public void batchCreate(List<T> entityList) {
    invalidateRelatedCaches();
    for (T entity : entityList) {
      getRepository().create(entity);
    }
  }

  public void edit(T entity) throws ConstraintViolationBagException {
    validateEntity(entity);
    invalidateRelatedCaches();
    getRepository().edit(entity);
  }

  protected void validateEntity(T entity) throws ConstraintViolationBagException {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);

    if (constraintViolations.size() > 0) {
      throw new ConstraintViolationBagException(constraintViolations);
    }
  }

  public void batchEdit(List<T> entityList) {
    invalidateRelatedCaches();
    for (T entity : entityList) {
      getRepository().edit(entity);
    }
  }

  public void remove(T entity) {
    invalidateRelatedCaches();
    getRepository().remove(entity);
  }

  public T find(Long id) {
    return getRepository().find(id);
  }

  public List<T> findAll() {
    return getRepository().findAll();
  }

  public List<T> findRange(int startPosition, int maxResult) {
    return getRepository().findRange(startPosition, maxResult);
  }

  public int count() {
    return getRepository().count();
  }

  protected void invalidateRelatedCaches() {

    for (String type : typesToClear()) {
      getCache().invalidateCache(type, null);
      getCache().invalidateCache("java.util.List", type);
    }
    getCache().invalidateCache("javax.ws.rs.core.Response", null);

  }

}
