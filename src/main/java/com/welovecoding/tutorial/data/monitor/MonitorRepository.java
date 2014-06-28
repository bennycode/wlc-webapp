package com.welovecoding.tutorial.data.monitor;

import com.welovecoding.tutorial.data.base.BaseRepository;
import com.welovecoding.tutorial.data.base.EJBLoggerInterceptor;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Koppen
 */
@Stateless
@Interceptors({EJBLoggerInterceptor.class})
public class MonitorRepository extends BaseRepository<Invocation> {

  public MonitorRepository() {
    super(Invocation.class);
  }

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

}
