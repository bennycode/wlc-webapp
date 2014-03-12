package de.fhb.repository;

import com.welovecoding.web.config.Names;
import de.fhb.entities.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
//@Interceptors({EJBLoggerInterceptor.class})
public class CategoryRepository extends AbstractRepository<Category> {

  @PersistenceContext(unitName = Names.PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CategoryRepository() {
    super(Category.class);
  }

  public List<Category> getCategoriesOrderedByName() {
    return em.createNamedQuery(Category.ORDER_BY_NAME, Category.class).getResultList();
  }
}
