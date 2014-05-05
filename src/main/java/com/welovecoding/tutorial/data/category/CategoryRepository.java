package com.welovecoding.tutorial.data.category;

import com.welovecoding.tutorial.data.base.BaseRepository;
import com.welovecoding.tutorial.data.category.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
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
}