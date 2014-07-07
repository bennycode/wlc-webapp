package com.welovecoding.tutorial.data.statistic.entity;

import static com.welovecoding.tutorial.data.statistic.entity.CacheDidCreateCacheStatistic.STATISTIC_TYPE_COLUMN_VALUE;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;

/**
 *
 * @author MacYser
 */
@Entity
@NamedQueries({})
@DiscriminatorValue(STATISTIC_TYPE_COLUMN_VALUE)
public class CacheDidCreateCacheStatistic extends CacheStatistic {

  protected static final String STATISTIC_TYPE_COLUMN_VALUE = "CACHE_DID_CREATE_CACHE";

  public CacheDidCreateCacheStatistic() {
  }

  public CacheDidCreateCacheStatistic(String name, int hits) throws Exception {
    super(name, hits);
  }

  public CacheDidCreateCacheStatistic(String name, Date from, Date to, int hits) throws Exception {
    super(name, from, to, hits);
  }

  public CacheDidCreateCacheStatistic(String name, Date from, long duration, TimeUnit unit, int hits) throws Exception {
    super(name, from, duration, unit, hits);
  }

}
