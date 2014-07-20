package com.welovecoding.tutorial.data.category;

import com.welovecoding.tutorial.data.base.BaseRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class CategoryRepository extends BaseRepository<Category> {

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CategoryRepository() {
    super(Category.class);
  }

  public List<Category> findAllOrderedByName() {
    return getEntityManager().createNamedQuery(Category.ORDER_BY_NAME, Category.class).getResultList();
  }

  public Category findBySlug(String slug) {
    Category result = null;
    try {
      result = getEntityManager().createNamedQuery(Category.FIND_BY_SLUG, Category.class).
              setParameter("categoryslug", slug).
              getSingleResult();
    } catch (NoResultException ex) {
      // NOP
    }
    return result;
  }

}
