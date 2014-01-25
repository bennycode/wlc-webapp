package de.fhb.repository;

import com.welovecoding.web.config.Names;
import de.fhb.model.Author;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AuthorRepository {

  @PersistenceContext(unitName = Names.PERSISTENCE_UNIT_NAME)
  EntityManager em;

  public List<Author> getCategories() {
    TypedQuery<Author> query = em.createNamedQuery("Author.findAll", Author.class);
    return query.getResultList();
  }

  public void save(Author author) {
    em.persist(author);
  }

}
