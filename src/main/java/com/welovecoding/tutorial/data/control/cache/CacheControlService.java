package com.welovecoding.tutorial.data.control.cache;

import static com.welovecoding.tutorial.data.base.BaseRepository.PERSISTENCE_UNIT_NAME;
import de.yser.ownsimplecache.OwnCacheServerService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Koppen
 */
@Stateless
public class CacheControlService {

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  private EntityManager em;

  @EJB
  private OwnCacheServerService cacheService;

  /**
   * NOTE: This method can cause a massive impact of the datebase in production!
   * Don't use it when it's RushHour.
   */
  public void clearDatabaseCache() {
    em.getEntityManagerFactory().getCache().evictAll();
  }

  /**
   * NOTE: This method can cause a massive impact of the datebase in production!
   * Don't use it when it's RushHour.
   */
  public void clearRestCache() {
    cacheService.invalidateAllCaches();
  }

}
