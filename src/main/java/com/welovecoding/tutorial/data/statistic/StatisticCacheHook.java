package com.welovecoding.tutorial.data.statistic;

import com.welovecoding.tutorial.data.statistic.entity.CacheDidCacheStatistic;
import com.welovecoding.tutorial.data.statistic.entity.CacheDidCreateCacheStatistic;
import com.welovecoding.tutorial.data.statistic.entity.CacheDidHitStatistic;
import com.welovecoding.tutorial.data.statistic.entity.CacheDidInvalidateAllCachesStatistic;
import com.welovecoding.tutorial.data.statistic.entity.CacheDidInvalidateCacheStatistic;
import de.yser.ownsimplecache.util.hook.Hook;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 *
 *
 * @author Michael Koppen
 */
public class StatisticCacheHook implements Hook {

  private StatisticService statisticService;
  private static final Logger LOG = Logger.getLogger(StatisticCacheHook.class.getName());

  public StatisticCacheHook() {

  }

  private void injectServices() {
    if (statisticService == null) {
      try {
        //possible versions of JNDI lookup
        statisticService = (StatisticService) new InitialContext().lookup("java:module/StatisticService");
//        monitorService = (StatisticService) new InitialContext().lookup("java:global/_We_Love_Coding__webapp_/MonitorService!com.welovecoding.tutorial.data.monitor.StatisticService");
//        monitorService = (StatisticService) new InitialContext().lookup("java:app/_We_Love_Coding__webapp_/MonitorService");
//        monitorService = (StatisticService) new InitialContext().lookup("java:global/_We_Love_Coding__webapp_/MonitorService");

        LOG.log(Level.INFO, "Successfully injected StatisticService!");
      } catch (NamingException ex) {
        LOG.log(Level.WARNING, "Injection of  StatisticService failed!");
      }
    }

  }

  @Override
  public void willCache(String objectOfClass, String withKey, String andValue) {
//    LOG.log(Level.INFO, "willCache object of class \"{0}\" with key \"{1}\" and value \"{2}\".", new Object[]{objectOfClass, withKey, andValue});
//    injectServices();
  }

  @Override
  public void didCache(String objectOfClass, String withKey, String andValue) {
    LOG.log(Level.INFO, "didCache object of class \"{0}\" with key \"{1}\" and value \"{2}\".", new Object[]{objectOfClass, withKey, andValue});
    injectServices();
    if (statisticService != null) {
      CacheDidCacheStatistic cacheStat = null;
      try {
        cacheStat = new CacheDidCacheStatistic(withKey, 1);
      } catch (Exception ex) {
        Logger.getLogger(StatisticCacheHook.class.getName()).log(Level.SEVERE, null, ex);
      }
      statisticService.create(cacheStat);
    }
  }

  @Override
  public void willGet(String objectOfClass, String withKey) {
//    LOG.log(Level.INFO, "willGet object of class \"{0}\" with key \"{1}\".", new Object[]{objectOfClass, withKey});
//    injectServices();
  }

  @Override
  public void didGet(String objectOfClass, String withKey) {
    LOG.log(Level.INFO, "didGet object of class \"{0}\" with key \"{1}\".", new Object[]{objectOfClass, withKey});
    injectServices();
    if (statisticService != null) {
      CacheDidHitStatistic cacheStat = null;
      try {
        cacheStat = new CacheDidHitStatistic(withKey, 1);
      } catch (Exception ex) {
        Logger.getLogger(StatisticCacheHook.class.getName()).log(Level.SEVERE, null, ex);
      }
      statisticService.create(cacheStat);
    }

  }

  @Override
  public void willInvalidateCache(String ofClass, String withHint) {
//    LOG.log(Level.INFO, "willInvalidateCache of class \"{0}\" with hint \"{1}\".", new Object[]{ofClass, withHint});
//    injectServices();
  }

  @Override
  public void didInvalidateCache(String ofClass, String withHint) {
    LOG.log(Level.INFO, "didInvalidateCache of class \"{0}\" with hint \"{1}\".", new Object[]{ofClass, withHint});
    injectServices();
    if (statisticService != null) {
      CacheDidInvalidateCacheStatistic cacheStat = null;
      try {
        cacheStat = new CacheDidInvalidateCacheStatistic(ofClass, 1);
      } catch (Exception ex) {
        Logger.getLogger(StatisticCacheHook.class.getName()).log(Level.SEVERE, null, ex);
      }
      statisticService.create(cacheStat);
    }
  }

  @Override
  public void willInvalidateAllCaches() {
//    LOG.log(Level.INFO, "willInvalidateAllCaches.");
//    injectServices();
  }

  @Override
  public void didInvalidateAllCaches() {
    LOG.log(Level.INFO, "didInvalidateAllCaches.");
    injectServices();
    if (statisticService != null) {
      CacheDidInvalidateAllCachesStatistic cacheStat = null;
      try {
        cacheStat = new CacheDidInvalidateAllCachesStatistic("ALL", 1);
      } catch (Exception ex) {
        Logger.getLogger(StatisticCacheHook.class.getName()).log(Level.SEVERE, null, ex);
      }
      statisticService.create(cacheStat);
    }
  }

  @Override
  public void willCreateCache(String name, String genericTypeHint, long expiringTime, TimeUnit unit) {
//    LOG.log(Level.INFO, "willCreateCache \"{0}\" with hint \"{1}\" and expiring time \"{2} {3}\".", new Object[]{name, genericTypeHint, expiringTime, unit.name()});
//    injectServices();
  }

  @Override
  public void didCreateCache(String name, String genericTypeHint, long expiringTime, TimeUnit unit) {
    LOG.log(Level.INFO, "didCreateCache \"{0}\" with hint \"{1}\" and expiring time \"{2} {3}\".", new Object[]{name, genericTypeHint, expiringTime, unit.name()});
    injectServices();
    if (statisticService != null) {
      CacheDidCreateCacheStatistic cacheStat = null;
      try {
        if (genericTypeHint != null && !genericTypeHint.isEmpty()) {
          cacheStat = new CacheDidCreateCacheStatistic(name + "<" + genericTypeHint + ">", 1);
        } else {
          cacheStat = new CacheDidCreateCacheStatistic(name, 1);
        }
      } catch (Exception ex) {
        Logger.getLogger(StatisticCacheHook.class.getName()).log(Level.SEVERE, null, ex);
      }
      statisticService.create(cacheStat);
    }
  }

}
