package com.welovecoding.repository;

import static com.welovecoding.config.Packages.PERSISTENCE_UNIT_NAME;
import com.welovecoding.entities.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CategoryRepository extends AbstractRepository<Category> {

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
}
