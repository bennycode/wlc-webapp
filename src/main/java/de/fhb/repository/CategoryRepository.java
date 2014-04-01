package de.fhb.repository;

import static de.fhb.config.Packages.PERSISTENCE_UNIT_NAME;
import de.fhb.entities.Category;
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
