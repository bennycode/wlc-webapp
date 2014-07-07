/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.welovecoding.tutorial.data.statistic.entity;

import static com.welovecoding.tutorial.data.statistic.entity.CacheDidInvalidateCacheStatistic.STATISTIC_TYPE_COLUMN_VALUE;
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
public class CacheDidInvalidateCacheStatistic extends CacheStatistic {

  protected static final String STATISTIC_TYPE_COLUMN_VALUE = "CACHE_DID_INVALIDATE_CACHE";

  public CacheDidInvalidateCacheStatistic() {
  }

  public CacheDidInvalidateCacheStatistic(String name, int hits) throws Exception {
    super(name, hits);
  }

  public CacheDidInvalidateCacheStatistic(String name, Date from, Date to, int hits) throws Exception {
    super(name, from, to, hits);
  }

  public CacheDidInvalidateCacheStatistic(String name, Date from, long duration, TimeUnit unit, int hits) throws Exception {
    super(name, from, duration, unit, hits);
  }

}
