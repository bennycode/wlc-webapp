package com.welovecoding.tutorial.data.statistic;

import com.welovecoding.tutorial.data.ConstraintViolationBagException;
import com.welovecoding.tutorial.data.base.EJBLoggerInterceptor;
import com.welovecoding.tutorial.data.statistic.entity.CacheStatistic;
import com.welovecoding.tutorial.data.statistic.entity.Statistic;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 *
 * @author Michael Koppen
 */
@Stateless
@Interceptors({EJBLoggerInterceptor.class})
public class StatisticService {

  private static final Logger LOG = Logger.getLogger(StatisticService.class.getName());

  @EJB
  private StatisticRepository repository;
  @EJB
  private OwnCacheServerService cacheService;

  public StatisticService() {
  }

  protected StatisticRepository getRepository() {
    return repository;
  }

  protected OwnCacheServerService getCache() {
    return cacheService;
  }

  /**
   * This method is to flush all temporary cached stats into the database.
   * Usually this method will be called on redeploy/undeploy of the app or
   * restart/stop of the server.
   */
  public void flush() {
    LOG.log(Level.INFO, "Saving Statistics before destroying Service.");
    try {
      getRepository().batchCreate(CacheStatistic.statisticCache.iterator());
      LOG.log(Level.INFO, "Successfully saved Statistics before destroyed Service.");
    } catch (Exception e) {
      LOG.log(Level.WARNING, "Failed to preserve Cache Statistics!");
    }
  }

  @Asynchronous
  public void create(Statistic entity) {
    if (entity instanceof CacheStatistic) {
      LOG.log(Level.INFO, "Entity is a CacheStatistic.");
      CacheStatistic cacheStat = (CacheStatistic) entity;
      if (CacheStatistic.statisticCache.isEmpty()) {
        LOG.log(Level.INFO, "Statistics-Cache is empty.");
        CacheStatistic.statisticCache.addLast(cacheStat);
      } else {

        Date startdate = CacheStatistic.statisticCache.getFirst().getFromDate();
        Date lastDate = cacheStat.getToDate();
        if (new Date(startdate.getTime() + CacheStatistic.SAVE_INTERVAL).before(lastDate)) {
          LOG.log(Level.INFO, "Time to save {0} Statistics to database.", new Object[]{CacheStatistic.statisticCache.size()});

          getRepository().batchCreate(CacheStatistic.statisticCache.iterator());

          CacheStatistic.statisticCache.clear();
          CacheStatistic.statisticCache.addLast(cacheStat);
        } else {
          CacheStatistic.statisticCache.addLast(cacheStat);
          LOG.log(Level.INFO, "First stat is from {0} with a save-interval of {1}ms and did not reached the save-interval with {2} - {3} Objects in Statistics-Cache.", new Object[]{startdate, CacheStatistic.SAVE_INTERVAL, lastDate, CacheStatistic.statisticCache.size()});
        }
      }
    } else {
      LOG.log(Level.WARNING, "Unknown Statistic!");
    }

  }

  public void edit(Statistic entity) throws ConstraintViolationBagException {
    validateEntity(entity);
    getRepository().edit(entity);
  }

  protected void validateEntity(Statistic entity) throws ConstraintViolationBagException {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<Statistic>> constraintViolations = validator.validate(entity);

    Iterator<ConstraintViolation<Statistic>> iterator = constraintViolations.iterator();
    while (iterator.hasNext()) {
      ConstraintViolation<Statistic> item = iterator.next();
      String property = item.getPropertyPath().toString();
      LOG.log(Level.INFO, "Validation error in ''{0}''. Reason: ''{1}''.", new Object[]{property, item.getMessage()});
    }

    if (constraintViolations.size() > 0) {
      throw new ConstraintViolationBagException(constraintViolations);
    }
  }

  public void remove(Statistic entity) {
    getRepository().remove(entity);
  }

  public Statistic find(Long id) {
    return getRepository().find(id);
  }

  public List<Statistic> findAll() {
    return getRepository().findAll();
  }

  public List<? extends Statistic> findAllByType(Class<? extends Statistic> type) {
    return getRepository().findAllByType(type);
  }

  public List<Statistic> findRange(int startPosition, int maxResult) {
    return getRepository().findRange(startPosition, maxResult);
  }

  public int count() {
    return getRepository().count();
  }

}
