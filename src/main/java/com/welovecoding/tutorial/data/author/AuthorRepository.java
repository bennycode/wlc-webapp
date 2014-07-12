package com.welovecoding.tutorial.data.author;

import com.welovecoding.tutorial.data.base.BaseRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuthorRepository extends BaseRepository<Author> {

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public AuthorRepository() {
    super(Author.class);
  }

  public List<Author> orderByName() {
    return em.createNamedQuery(Author.ORDER_BY_NAME, Author.class).getResultList();
  }

}
