/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.repository;

import com.welovecoding.web.config.Names;
import de.fhb.entities.BaseEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MacYser
 */
@Stateless
//@Interceptors({EJBLoggerInterceptor.class})
public class BaseEntityRepository extends AbstractRepository<BaseEntity> {

  public BaseEntityRepository() {
    super(BaseEntity.class);
  }

  @PersistenceContext(unitName = Names.PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }
}
