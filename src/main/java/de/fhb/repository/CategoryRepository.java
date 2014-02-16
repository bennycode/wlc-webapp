package de.fhb.repository;

import com.welovecoding.web.config.Names;
import de.fhb.entities.Category;
import de.fhb.logging.interceptor.EJBLoggerInterceptor;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Benny Neugebauer (bn@bennyn.de)
 */
@Stateless
@Interceptors({EJBLoggerInterceptor.class})
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
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c ORDER BY c.name", Category.class);
		return query.getResultList();
	}
}
