package com.welovecoding.tutorial.data.statistic.entity;

import static com.welovecoding.tutorial.data.statistic.entity.CacheDidInvalidateAllCachesStatistic.STATISTIC_TYPE_COLUMN_VALUE;
import java.util.Date;
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
public class CacheDidInvalidateAllCachesStatistic extends CacheStatistic {

  protected static final String STATISTIC_TYPE_COLUMN_VALUE = "CACHE_DID_INVALIDATE_ALL_CACHES";

  public CacheDidInvalidateAllCachesStatistic() {
  }

  public CacheDidInvalidateAllCachesStatistic(String name, int hits) throws Exception {
    super(name, hits);
  }

  public CacheDidInvalidateAllCachesStatistic(String name, Date from, Date to, int hits) throws Exception {
    super(name, from, to, hits);
  }

  public CacheDidInvalidateAllCachesStatistic(String name, Date from, long duration, TimeUnit unit, int hits) throws Exception {
    super(name, from, duration, unit, hits);
  }

}
