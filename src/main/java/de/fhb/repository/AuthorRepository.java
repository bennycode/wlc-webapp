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

  public long getAuthorCount() {
    return em.createNamedQuery("Author.getCount", Long.class).getSingleResult().intValue();
  }

  public List<Author> getCategories() {
    TypedQuery<Author> query = em.createNamedQuery("Author.findAll", Author.class);
    return query.getResultList();
  }

  public void save(Author author) {
    em.merge(author);
  }

  public void delete(Author author) {
    Author managedAuthor = em.getReference(Author.class, author.getId());
    em.remove(managedAuthor);
  }

}
