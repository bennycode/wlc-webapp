package com.welovecoding.web.registration;

import com.welovecoding.config.Packages;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserRepository {

  @PersistenceContext(unitName = Packages.PERSISTENCE_UNIT_NAME)
  EntityManager em;

  public UserRepository() {
  }

  public void save(UserEntity user) {
    em.persist(user);
    em.flush();
  }
}
