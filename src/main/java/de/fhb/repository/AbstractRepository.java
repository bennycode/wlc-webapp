package de.fhb.repository;

import de.fhb.entities.BaseEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * TODO: http://stackoverflow.com/questions/197986/what-causes-javac-to-issue-the-uses-unchecked-or-unsafe-operations-warning
 * 
 * @author Benny Neugebauer (bn@bennyn.de)
 * @param <T extends BaseEntity>
 */
public abstract class AbstractRepository<T extends BaseEntity> {

  protected abstract EntityManager getEntityManager();
  private final Class<T> entityClass;

  @SuppressWarnings("unchecked")
  public AbstractRepository(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  @SuppressWarnings("unchecked")
  public T create(T entity) {
    getEntityManager().persist(entity);
    getEntityManager().flush();
    return this.edit(entity);
  }

  @SuppressWarnings("unchecked")
  public T edit(T entity) {
    return getEntityManager().merge(entity);
  }

  @SuppressWarnings("unchecked")
  public void remove(T entity) {
    getEntityManager().remove(getEntityManager().merge(entity));
  }

  @SuppressWarnings("unchecked")
  public T find(Object id) {
    return getEntityManager().find(entityClass, id);
  }

  @SuppressWarnings("unchecked")
  public List<T> findAll() {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    return getEntityManager().createQuery(cq).getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<T> findRange(int[] range) {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    Query q = getEntityManager().createQuery(cq);
    q.setMaxResults(range[1] - range[0] + 1);
    q.setFirstResult(range[0]);
    return q.getResultList();
  }

  @SuppressWarnings("unchecked")
  public int count() {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    Root<T> rt = cq.from(entityClass);
    cq.select(getEntityManager().getCriteriaBuilder().count(rt));
    Query q = getEntityManager().createQuery(cq);
    return ((Long) q.getSingleResult()).intValue();
  }

}
