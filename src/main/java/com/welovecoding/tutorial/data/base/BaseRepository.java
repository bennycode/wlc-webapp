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
import javax.persistence.PersistenceException;
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

  private static final Logger LOG = Logger.getLogger(BaseRepository.class.getName());

  public static final String PERSISTENCE_UNIT_NAME = "com.welovecoding.web_wlc-webapp_war_1.0-SNAPSHOTPU";

  protected abstract EntityManager getEntityManager();
  private final Class<T> entityClass;

  public BaseRepository(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  /**
   * Associates a new ID to an entity and persists it. If any exception is
   * thrown the method will return null.
   *
   * @param entity entity to persist
   * @return persisted and merged entity with new ID or null
   */
  public T create(T entity) {
    T result = null;
    try {
      getEntityManager().persist(entity);
      getEntityManager().flush();
      evictNestedEntities(entity);
      result = this.edit(entity);
    } catch (IllegalArgumentException | PersistenceException e) {
      LOG.log(Level.SEVERE, "Could not persist entity!", e);
    }
    return result;
  }

  /**
   * Merges an entity with the underlying database data. If any exception is
   * thrown the method will return null.
   *
   * @param entity entity to merge
   * @return merged entity or null
   */
  public T edit(T entity) {
    T result = null;
    try {
      entity = getEntityManager().merge(entity);
      evictNestedEntities(entity);
      result = entity;
    } catch (IllegalArgumentException | PersistenceException e) {
      LOG.log(Level.SEVERE, "Could not merge entity!", e);
    }
    return result;
  }

  /**
   * Removes an entity from database and context. This method will silently
   * discard the operation if an exception is thrown.
   *
   * @param entity entity to remove
   */
  public void remove(T entity) {
    try {
      getEntityManager().remove(getEntityManager().merge(entity));
      evictNestedEntities(entity);
    } catch (IllegalArgumentException | PersistenceException e) {
      LOG.log(Level.SEVERE, "Could not remove entity!", e);
    }
  }

  public void batchCreate(Iterator<T> entities) {
    try {
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
    } catch (IllegalArgumentException | PersistenceException e) {
      LOG.log(Level.SEVERE, "Could not persist entities!", e);
    }
  }

  public void batchEdit(Iterator<T> entities) {
    try {
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
    } catch (IllegalArgumentException | PersistenceException e) {
      LOG.log(Level.SEVERE, "Could not persist entities!", e);
    }
  }

  public void batchRemove(Iterator<T> entities) {
    try {
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
    } catch (IllegalArgumentException | PersistenceException e) {
      LOG.log(Level.SEVERE, "Could not persist entities!", e);
    }
  }

  public T find(Object id) {
    T result = null;
    try {
      result = getEntityManager().find(entityClass, id);
    } catch (IllegalArgumentException e) {
      LOG.log(Level.SEVERE, "Could not find entity!", e);
    }
    return result;
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
   * This method iterates all fields of the given entity and evicts all nested
   * Entities of class BaseEntity from the database cache. Note: This method
   * will not evict entities which are nested in Objects with a generic type of
   * BaseEntity due to the type-erasure
   *
   * @param entity
   */
  protected void evictNestedEntities(T entity) {
    if (entity != null) {
      Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Evicting nested entities");
      for (Field field : entity.getClass().getDeclaredFields()) {
        Logger.getLogger(this.getClass().getName()).log(Level.FINEST, "Field {0}", field.getName());
        field.setAccessible(true);

        // check if class of field is a subtype of BaseEntity
        if (BaseEntity.class.isAssignableFrom(field.getType())) {
          Logger.getLogger(this.getClass().getName()).log(Level.FINEST, "Found nested entity");
          try {
            evict((BaseEntity) field.get(entity));
          } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }

  }

  protected void evict(BaseEntity objectToEvict) {
    if (objectToEvict != null) {
      if (objectToEvict.getId() != null) {
        // evicting one entity with id
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Evicting one entity of type {0} with id {1} and name {2}", new Object[]{objectToEvict.getClass().getName(), objectToEvict.getId(), objectToEvict.getName()});
        getEntityManager().getEntityManagerFactory().getCache().evict(objectToEvict.getClass(), objectToEvict.getId());
      } else {
        // evicting all entities of type
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Evicting all entities of type {0}", new Object[]{objectToEvict.getClass().getName()});
        getEntityManager().getEntityManagerFactory().getCache().evict(objectToEvict.getClass());
      }
    }
  }
}
