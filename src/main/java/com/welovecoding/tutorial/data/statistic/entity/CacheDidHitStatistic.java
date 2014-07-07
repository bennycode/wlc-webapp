package com.welovecoding.tutorial.data.statistic.entity;

import static com.welovecoding.tutorial.data.statistic.entity.CacheDidHitStatistic.STATISTIC_TYPE_COLUMN_VALUE;
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
public class CacheDidHitStatistic extends CacheStatistic {

  protected static final String STATISTIC_TYPE_COLUMN_VALUE = "CACHE_DID_HIT";

  public CacheDidHitStatistic() {
  }

  public CacheDidHitStatistic(String name, int hits) throws Exception {
    super(name, hits);
  }

  public CacheDidHitStatistic(String name, Date from, Date to, int hits) throws Exception {
    super(name, from, to, hits);
  }

  public CacheDidHitStatistic(String name, Date from, long duration, TimeUnit unit, int hits) throws Exception {
    super(name, from, duration, unit, hits);
  }

}
