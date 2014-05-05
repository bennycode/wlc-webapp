package com.welovecoding.repository;

import static com.welovecoding.config.Packages.PERSISTENCE_UNIT_NAME;
import com.welovecoding.entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class UserRepository extends AbstractRepository<User> {

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public UserRepository() {
    super(User.class);
  }

  public User findByEmail(String email) {
    User user = null;

    try {
      user = em.createNamedQuery(User.FIND_BY_EMAIL, User.class)
              .setParameter("email", email)
              .getSingleResult();
    } catch (NoResultException ex) {
      // NOP
    }

    return user;
  }

}
