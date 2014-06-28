package com.welovecoding.tutorial.data.monitor;

import com.welovecoding.tutorial.data.base.BaseService;
import com.welovecoding.tutorial.data.base.EJBLoggerInterceptor;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author Michael Koppen
 */
@Stateless
@Interceptors({EJBLoggerInterceptor.class})
public class MonitorService extends BaseService<Invocation, MonitorRepository> {

  @EJB
  private MonitorRepository repository;
  @EJB
  private OwnCacheServerService cacheService;

  public MonitorService() {
    super(Invocation.class);
  }

  @Override
  protected MonitorRepository getRepository() {
    return repository;
  }

  @Override
  protected OwnCacheServerService getCache() {
    return cacheService;
  }

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(/*Nothing*/);
  }

}
