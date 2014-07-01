package com.welovecoding.tutorial.data.base;

import com.welovecoding.tutorial.data.ConstraintViolationBagException;
import com.welovecoding.tutorial.data.monitor.MonitorCacheHook;
import com.welovecoding.tutorial.data.monitor.MonitorInterceptor;
import de.yser.ownsimplecache.OwnCacheServerService;
import de.yser.ownsimplecache.util.hook.Hook;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.Interceptors;
import javax.persistence.MappedSuperclass;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

@MappedSuperclass
@Interceptors({EJBLoggerInterceptor.class, MonitorInterceptor.class})
public abstract class BaseService<T extends BaseEntity, E extends BaseRepository<T>> {

  private static final Logger LOG = Logger.getLogger(BaseService.class.getName());

  private final Class<T> entityClass;

  protected abstract E getRepository();

  protected abstract OwnCacheServerService getCache();

  protected abstract Set<String> typesToClear();

  static {
    try {
      OwnCacheServerService.registerHooks(Hook.class, MonitorCacheHook.class);
    } catch (Exception ex) {
      Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

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

    Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
    while (iterator.hasNext()) {
      ConstraintViolation<T> item = iterator.next();
      String property = item.getPropertyPath().toString();
      LOG.log(Level.INFO, "Validation error in ''{0}''. Reason: ''{1}''.", new Object[]{property, item.getMessage()});
    }

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
