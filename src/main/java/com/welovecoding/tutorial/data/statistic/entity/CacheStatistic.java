package com.welovecoding.tutorial.data.statistic.entity;

import static com.welovecoding.tutorial.data.statistic.entity.CacheStatistic.STATISTIC_TYPE_COLUMN_VALUE;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;

/**
 *
 * @author Michael Koppen
 */
@Entity
@NamedQueries({})
@DiscriminatorValue(STATISTIC_TYPE_COLUMN_VALUE)
public class CacheStatistic extends Statistic {

  protected static final String STATISTIC_TYPE_COLUMN_VALUE = "CACHE";
  public static final LinkedBlockingDeque<Statistic> statisticCache = new LinkedBlockingDeque<>();
  public static final long SAVE_INTERVAL = 300000;// 5 Minutes
//  public static final long SAVE_INTERVAL = 30000;// 30 Seconds

  public CacheStatistic() {
  }

  public CacheStatistic(String name, int hits) throws Exception {
    super(name, hits);
  }

  public CacheStatistic(String name, Date from, Date to, int hits) throws Exception {
    super(name, from, to, hits);
  }

  public CacheStatistic(String name, Date from, long duration, TimeUnit unit, int hits) throws Exception {
    super(name, from, duration, unit, hits);
  }

}
