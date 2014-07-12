package com.welovecoding.tutorial.view.control.cache;

import com.welovecoding.tutorial.data.control.cache.CacheControlService;
import com.welovecoding.tutorial.data.statistic.StatisticService;
import com.welovecoding.tutorial.data.statistic.entity.CacheDidHitStatistic;
import com.welovecoding.tutorial.data.statistic.entity.Statistic;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    Map<String, List<Statistic>> hits = statService.findAllBetweenByTypeWithIntervall(
            CacheDidHitStatistic.class,
            cutTillDay(new Date(new Date().getTime() - (86400000 * 3))),//last 3 days
            new Date(),
            5,
            TimeUnit.MINUTES);

    /**
     * Create JSON which looks like this:
     *
     * [
     * {
     * data:[
     * [1405071000000,5],[1405071600000,0],[1405072200000,1],[1405072800000,1],
     * [1405073400000,2],[1405074000000,3],[1405074600000,2],[1405075200000,2]
     * ], label: 'Overall Hits' } ]
     */
    StringBuilder json = new StringBuilder();
    json.append("[");
    for (Map.Entry<String, List<Statistic>> entry : hits.entrySet()) {
      List<Statistic> list = entry.getValue();

      json.append("\n{data:[");
      for (int i = 0; i < list.size(); i++) {
        Statistic statistic = list.get(i);

        json.append("[");

        json.append(statistic.getFromDate().getTime());
        json.append(",").append(statistic.getHits());

        json.append("]");

        if (i < (list.size() - 1)) {
          json.append(",");
        }
      }
      json.append("],\n");
      json.append("label: 'Hits of ").append(entry.getKey()).append("',");
      if (entry.getKey().equalsIgnoreCase("getCategories")) {
        json.append("color: '#3333FF'");
      } else if (entry.getKey().equalsIgnoreCase("Everything")) {
        json.append("color: '#0099CC'");
      }
      json.append("},");

    }
    // delete last ,
    json.deleteCharAt(json.length() - 1);

    json.append("]");
    return json.toString();
  }

  //TODO move cutTillDay to Service and optimize
  private static Date cutTillDay(Date date) {
    Date newDate = new Date(date.getYear(), date.getMonth(), date.getDate());
    return newDate;
  }
}
