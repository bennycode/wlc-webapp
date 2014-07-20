package com.welovecoding.tutorial.data.author;

import com.google.api.client.util.Lists;
import com.welovecoding.tutorial.data.base.BaseRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Stateless
public class AuthorRepository extends BaseRepository<Author> {

  private static final Logger LOG = Logger.getLogger(AuthorRepository.class.getName());

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public AuthorRepository() {
    super(Author.class);
  }

  public List<Author> findAllOrderedByName() {
    List<Author> result = Lists.newArrayList();
    try {
      result = getEntityManager().createNamedQuery(Author.ORDER_BY_NAME, Author.class).getResultList();
    } catch (IllegalStateException | PersistenceException e) {
      LOG.log(Level.SEVERE, "Could not get Authors!", e);
    }
    return result;
  }

}
