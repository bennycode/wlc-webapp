package com.welovecoding.web.registration;

import com.welovecoding.web.config.Names;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserRepository {
  @PersistenceContext(unitName = Names.PERSISTENCE_UNIT_NAME)
  EntityManager em;

  public UserRepository() {
  }

  public void save(UserEntity user) {
    em.persist(user);
    em.flush();
  }
}
