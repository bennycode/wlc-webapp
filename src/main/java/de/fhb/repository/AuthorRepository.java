package de.fhb.repository;

import com.welovecoding.web.config.Names;
import de.fhb.entities.Author;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuthorRepository extends AbstractRepository<Author> {

  @PersistenceContext(unitName = Names.PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public AuthorRepository() {
    super(Author.class);
  }

  public long getAuthorCount() {
    return em.createNamedQuery(Author.GET_COUNT, Long.class).getSingleResult().intValue();
  }

  public List<Author> getAuthors() {
    return em.createNamedQuery(Author.FIND_ALL, Author.class).getResultList();
  }

  public List<Author> getAuthors(int offset, int amount) {
    return em.createNamedQuery(Author.FIND_ALL, Author.class)
            .setFirstResult(offset)
            .setMaxResults(amount)
            .getResultList();
  }

  public void delete(Author author) {
    Author managedAuthor = em.getReference(Author.class, author.getId());
    em.remove(managedAuthor);
  }
}
