package com.welovecoding.service;

import com.welovecoding.config.Packages;
import com.welovecoding.controller.GenFormBaseController;
import com.welovecoding.entities.BaseEntity;
import com.welovecoding.repository.AbstractRepository;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.MappedSuperclass;
import javax.validation.ConstraintViolation;
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

  public void edit(T entity) {
    FacesContext context = FacesContext.getCurrentInstance();
    ResourceBundle backendText = context.getApplication().getResourceBundle(context, Packages.BACKEND_MESSAGES_NAME);

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);

    if (constraintViolations.size() > 0) {
      // TODO: Don't handle exception here - Throw it!
      for (ConstraintViolation<T> constraintViolation : constraintViolations) {

        String key;

        try {
          key = backendText.getString("admin.form.label." + constraintViolation.getPropertyPath());
        } catch (java.util.MissingResourceException ex) {
          // TODO: Should be combined with text handling in ComponentFactory
          key = constraintViolation.getPropertyPath().toString();
        }

        String summary = key + ": " + constraintViolation.getMessage();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        context.addMessage(GenFormBaseController.ERROR_MESSAGES_NAME, message);
      }

    } else {
      invalidateRelatedCaches();
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
