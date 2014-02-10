package com.welovecoding.web.data;

import com.welovecoding.web.entities.Category;
import com.welovecoding.web.config.Names;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CategoryRepository {

  @PersistenceContext(unitName = Names.PERSISTENCE_UNIT_NAME)
  EntityManager em;

  public CategoryRepository() {
  }

  public List<Category> getCategories() {
    TypedQuery<Category> query = em.createNamedQuery("Category.findAll", Category.class);
    return query.getResultList();
  }

  public List<Category> getCategoriesOrderedByTitle() {
    TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c ORDER BY c.title", Category.class);
    return query.getResultList();
  }

  public void save(Category item) {
    em.merge(item);
  }

  public void delete(Category item) {
    Category managedItem = em.getReference(Category.class, item.getId());
    em.remove(managedItem);
  }
}
