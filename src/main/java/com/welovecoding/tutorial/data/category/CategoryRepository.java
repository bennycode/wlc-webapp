package com.welovecoding.tutorial.data.category;

import com.welovecoding.tutorial.data.base.BaseRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
//@Interceptors({EJBLoggerInterceptor.class, MonitorInterceptor.class})
public class CategoryRepository extends BaseRepository<Category> {

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CategoryRepository() {
    super(Category.class);
  }

  public List<Category> orderByName() {
    return em.createNamedQuery(Category.ORDER_BY_NAME, Category.class).getResultList();
  }

  public Category getBySlug(String slug) {
    Category result = null;
    try {
      result = em.createNamedQuery(Category.FIND_BY_SLUG, Category.class).
              setParameter("categoryslug", slug).
              getSingleResult();
    } catch (NoResultException ex) {
      // NOP
    }
    return result;
  }

}
