package com.welovecoding.tutorial.data.base;

import com.google.api.client.util.Lists;
import com.welovecoding.tutorial.data.statistic.StatisticInterceptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Benny Neugebauer (bn@bennyn.de)
 * @author Michael Koppen (michael.koppen@googlemail.com)
 * @param <T extends Serializable>
 */
@MappedSuperclass
@Interceptors({EJBLoggerInterceptor.class, StatisticInterceptor.class})
public abstract class BaseRepository<T extends Serializable> {

  public static final String PERSISTENCE_UNIT_NAME = "com.welovecoding.web_wlc-webapp_war_1.0-SNAPSHOTPU";

  protected abstract EntityManager getEntityManager();
  private final Class<T> entityClass;

  public BaseRepository(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public T create(T entity) {
    getEntityManager().persist(entity);
    getEntityManager().flush();
    evictNestedEntities(entity);
    return this.edit(entity);
  }

  public T edit(T entity) {
    evictNestedEntities(entity);
    entity = getEntityManager().merge(entity);
    evictNestedEntities(entity);
    return entity;
  }

  public void remove(T entity) {
    getEntityManager().remove(getEntityManager().merge(entity));
    evictNestedEntities(entity);
  }

  public void batchCreate(Iterator<T> entities) {
    List<T> entityList = Lists.newArrayList(entities);
    for (int i = 0; i < entityList.size(); i++) {
      getEntityManager().persist(entityList.get(i));
      evictNestedEntities(entityList.get(i));
      // 100 is the default max batch-size. Mod 99 because we start with 0. See persistence.xml for changes
      // TODO get batchSize out of properties
      if (i != 0 && (i % 99) == 0) {
        getEntityManager().flush();
      }
    }
    getEntityManager().flush();
  }

  public void batchEdit(Iterator<T> entities) {
    List<T> entityList = Lists.newArrayList(entities);
    for (int i = 0; i < entityList.size(); i++) {
      getEntityManager().merge(entityList.get(i));
      evictNestedEntities(entityList.get(i));
      // 100 is the default max batch-size. Mod 99 because we start with 0. See persistence.xml for changes
      // TODO get batchSize out of properties
      if (i != 0 && (i % 99) == 0) {
        getEntityManager().flush();
      }
    }
    getEntityManager().flush();
  }

  public void batchRemove(Iterator<T> entities) {
    List<T> entityList = Lists.newArrayList(entities);
    for (int i = 0; i < entityList.size(); i++) {
      getEntityManager().remove(getEntityManager().merge(entityList.get(i)));
      evictNestedEntities(entityList.get(i));
      // 100 is the default max batch-size. Mod 99 because we start with 0. See persistence.xml for changes
      // TODO get batchSize out of properties
      if (i != 0 && (i % 99) == 0) {
        getEntityManager().flush();
      }
    }
    getEntityManager().flush();

  }

  public T find(Object id) {
    return getEntityManager().find(entityClass, id);
  }

  public List<T> findAll() {
    CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
    cq.select(cq.from(entityClass));
    return getEntityManager().createQuery(cq).getResultList();
  }

  public List<T> findRange(int[] range) {
    CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
    cq.select(cq.from(entityClass));
    TypedQuery<T> q = getEntityManager().createQuery(cq);
    q.setMaxResults(range[1] - range[0] + 1);
    q.setFirstResult(range[0]);
    return q.getResultList();
  }

  public List<T> findRange(int startPosition, int maxResult) {
    List<T> resultList;

    CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
    cq.select(cq.from(entityClass));

    TypedQuery<T> query = getEntityManager().createQuery(cq);
    resultList = query.setFirstResult(startPosition).setMaxResults(maxResult).getResultList();

    return resultList;
  }

  public int count() {
    CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
    Root rt = cq.from(entityClass);
    cq.select(getEntityManager().getCriteriaBuilder().count(rt));
    TypedQuery<Long> q = getEntityManager().createQuery(cq);
    return q.getSingleResult().intValue();
  }

  /**
   * This method iterates all fields of the given entity and evicts alls nested
   * Entities of class BaseEntity from the database cache. Note: This method
   * will not evict entities which are nested in Objects with a generic type of
   * BaseEntity due to the type-erasure
   *
   * @param entity
   */
  private void evictNestedEntities(T entity) {
    Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Evicting nested entities");
    for (Field field : entity.getClass().getDeclaredFields()) {
      Logger.getLogger(this.getClass().getName()).log(Level.FINEST, "Field {0}", field.getName());
      field.setAccessible(true);

      // check if class of field is a subtype of BaseEntity
      if (BaseEntity.class.isAssignableFrom(field.getType())) {
        Logger.getLogger(this.getClass().getName()).log(Level.FINEST, "found nested entity");
        try {
          evict((BaseEntity) field.get(entity));
        } catch (IllegalArgumentException | IllegalAccessException ex) {
          Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
      }
    }

  }

  private void evict(BaseEntity objectToEvict) {
    if (objectToEvict != null) {
      if (objectToEvict.getId() != null) {
        // evicting one entity with id
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "evicting one entity of type {0} with id {1} and name {2}", new Object[]{objectToEvict.getClass().getName(), objectToEvict.getId(), objectToEvict.getName()});
        getEntityManager().getEntityManagerFactory().getCache().evict(objectToEvict.getClass(), objectToEvict.getId());
      } else {
        // evicting all entities of type
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "evicting all entities of type {0}", new Object[]{objectToEvict.getClass().getName()});
        getEntityManager().getEntityManagerFactory().getCache().evict(objectToEvict.getClass());
      }
    }
  }
}
