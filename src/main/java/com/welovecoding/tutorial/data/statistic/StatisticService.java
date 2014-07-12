package com.welovecoding.tutorial.data.statistic;

import com.welovecoding.tutorial.data.ConstraintViolationBagException;
import com.welovecoding.tutorial.data.base.EJBLoggerInterceptor;
import com.welovecoding.tutorial.data.statistic.entity.CacheStatistic;
import com.welovecoding.tutorial.data.statistic.entity.Statistic;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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

  public Map<String, List<Statistic>> findAllBetweenByTypeWithIntervall(Class<? extends Statistic> type, Date fromDate, Date toDate, int interval, TimeUnit unit) {

    Map<String, List<Statistic>> reducedStatisticsWithKey = new HashMap<>();
    List<Statistic> reducedStatistics = createStatisticReduceTemplate(fromDate, toDate, interval, unit);

    List<Statistic> allStats = getRepository().findAllBetweenByType(type, fromDate, toDate);
    //fill map
    Set<String> allKeys = new HashSet<>();
    System.out.println("All Keys");
    for (Statistic stat : allStats) {
      System.out.println(stat.getName());
      allKeys.add(stat.getName());
    }

    for (Statistic statistic : reducedStatistics) {

      List<Statistic> statsInInterval = getRepository().findAllBetweenByType(type, statistic.getFromDate(), statistic.getToDate());

      Map<String, Statistic> keyStatInIntervalMap = new HashMap<>();
      for (String key : allKeys) {
        try {
          keyStatInIntervalMap.putIfAbsent(key, new Statistic(key, statistic.getFromDate(), statistic.getToDate(), 0));
        } catch (Exception ex) {
          Logger.getLogger(StatisticService.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

      // order stats by key values
      for (Statistic statInInterval : statsInInterval) {

        Statistic putIfAbsent;
        Statistic putIfAbsentAll;
        try {
          putIfAbsent = keyStatInIntervalMap.putIfAbsent(statInInterval.getName(), new Statistic(statInInterval.getName(), statistic.getFromDate(), statistic.getToDate(), 1));
          if (putIfAbsent != null) {
            putIfAbsent.setHits(putIfAbsent.getHits() + 1);
          }
          putIfAbsentAll = keyStatInIntervalMap.putIfAbsent("Everything", new Statistic("Everything", statistic.getFromDate(), statistic.getToDate(), 1));
          if (putIfAbsentAll != null) {
            putIfAbsentAll.setHits(putIfAbsentAll.getHits() + 1);
          }
        } catch (Exception ex) {
          Logger.getLogger(StatisticService.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

      for (Map.Entry<String, Statistic> entry : keyStatInIntervalMap.entrySet()) {
        List<Statistic> category = reducedStatisticsWithKey.get(entry.getKey());
        if (category == null) {
          category = new LinkedList<>();
          reducedStatisticsWithKey.put(entry.getKey(), category);
          category.add(entry.getValue());
        } else {
          category.add(entry.getValue());
        }

      }
    }

    return reducedStatisticsWithKey;
  }

  public List<Statistic> findRange(int startPosition, int maxResult) {
    return getRepository().findRange(startPosition, maxResult);
  }

  public int count() {
    return getRepository().count();
  }

  private List<Statistic> createStatisticReduceTemplate(Date fromDate, Date toDate, int interval, TimeUnit unit) {
    List<Statistic> reducedStatisticsTemplate = new ArrayList<>();

    Date lastDate = fromDate;
    while (lastDate.before(toDate)) {
      Statistic lastStat;
      try {
        lastStat = new Statistic("Hits", lastDate, unit.toMillis(interval) - 1, TimeUnit.MILLISECONDS, 0);
        lastDate = new Date(lastStat.getToDate().getTime() + 1);
        if (!lastDate.before(toDate)) {
          lastStat.setToDate(toDate);
          lastStat.setDuration(lastStat.getToDate().getTime() - lastStat.getFromDate().getTime());
        }
        reducedStatisticsTemplate.add(lastStat);
      } catch (Exception ex) {
        Logger.getLogger(StatisticService.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    return reducedStatisticsTemplate;
  }

}
