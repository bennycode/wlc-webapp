package com.welovecoding.tutorial.view.control.cache;

import com.welovecoding.tutorial.data.control.cache.CacheControlService;
import com.welovecoding.tutorial.data.statistic.StatisticService;
import com.welovecoding.tutorial.data.statistic.entity.CacheDidHitStatistic;
import com.welovecoding.tutorial.data.statistic.entity.Statistic;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen
 */
@Named
@RequestScoped
public class CacheController {

  @EJB
  private CacheControlService cacheService;
  @EJB
  private StatisticService statService;

  public void clearRestCache() {
    cacheService.clearRestCache();
  }

  public void clearDatabaseCache() {
    cacheService.clearDatabaseCache();
  }

  //TODO let jersey map to a JSON
  public String getCacheHitStatsOfLastThreeDaysJSON() {
    //TODO move cutTillDay to Service and optimize
    List<Statistic> hits = statService.findAllBetweenByTypeWithIntervall(
            CacheDidHitStatistic.class,
            cutTillDay(new Date(new Date().getTime() - (86400000 * 3))),
            new Date(),
            10,
            TimeUnit.MINUTES);

    StringBuilder json = new StringBuilder();
    json.append("[{data:[");
    for (int i = 0; i < hits.size(); i++) {
      Statistic statistic = hits.get(i);

      json.append("[");

      json.append(statistic.getFromDate().getTime());
      json.append(",").append(statistic.getHits());

      json.append("]");

      if (i < (hits.size() - 1)) {
        json.append(",\n");
      }
    }
    json.append("],");
    json.append("label: 'Overall Hits'");
    json.append("}");

//    json.append(",");
//    json.append("{data:[");
//    for (int i = 0; i < hits.size(); i++) {
//      json.append("[");
//
//      json.append(hits.get(i).getFromDate().getTime());
//      json.append(",").append(((int) (Math.random() * 55)));
//
//      json.append("]");
//      if (i < (hits.size() - 1)) {
//        json.append(",\n");
//      }
//    }
//    json.append("],");
//    json.append("label: 'Test Hits'");
//    json.append("}");
    json.append("]");
    return json.toString();
  }

  //TODO move cutTillDay to Service and optimize
  private static Date cutTillDay(Date date) {
    Date newDate = new Date(date.getYear(), date.getMonth(), date.getDate());
    return newDate;
  }
}
