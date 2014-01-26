package de.fhb.repository;

import com.welovecoding.web.config.Names;
import de.fhb.model.Author;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuthorRepository {

  @PersistenceContext(unitName = Names.PERSISTENCE_UNIT_NAME)
  EntityManager em;

  public long getAuthorCount() {
    return em.createNamedQuery("Author.getCount", Long.class).getSingleResult().intValue();
  }

  public List<Author> getAuthors() {
    return em.createNamedQuery("Author.findAll", Author.class).getResultList();
  }

  public List<Author> getAuthors(int offset, int amount) {
    return em.createNamedQuery("Author.findAll", Author.class)
            .setFirstResult(offset)
            .setMaxResults(amount)
            .getResultList();
  }

  public void save(Author author) {
    em.merge(author);
  }

  public void delete(Author author) {
    Author managedAuthor = em.getReference(Author.class, author.getId());
    em.remove(managedAuthor);
  }

}
