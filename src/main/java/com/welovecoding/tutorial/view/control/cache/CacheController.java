package com.welovecoding.tutorial.view.control.cache;

import com.google.api.client.util.Lists;
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

  public String getTodayCacheHitStatJSON() throws Exception {
    List<Statistic> hits = statService.findAllByType(CacheDidHitStatistic.class);

    List<Statistic> dailyHitList = Lists.newArrayList();
    Date today = cutTillDay(new Date(new Date().getTime() - 82800000));
    System.out.println("Creating new dailyHits for Date " + today);
    Statistic dailyHits = new Statistic("Hits", today, 1, TimeUnit.HOURS, 0);
    for (Statistic statistic : hits) {
      System.out.println("Watching for stats in between " + dailyHits.getFromDate() + " and " + dailyHits.getToDate());
      System.out.println("Hit: \n"
              + "ID: " + statistic.getId() + "\n"
              + "Name: " + statistic.getName() + "\n"
              + "Date: " + statistic.getFromDate() + "\n"
              + "");

      if (statistic.getFromDate().before(dailyHits.getFromDate())) {
        //skipping old stat
        System.out.println("skipping cuz statistic to old");
        continue;
      }
      if (statistic.getFromDate().after(dailyHits.getToDate())) {
        // fill next hour
        System.out.println("Next Hour");
        dailyHitList.add(dailyHits);
        dailyHits = new Statistic("Hits", dailyHits.getToDate(), 1, TimeUnit.HOURS, 0);
      }

      //between
      if (dailyHits.getFromDate().before(statistic.getFromDate()) && dailyHits.getToDate().after(statistic.getToDate())) {

        System.out.println("Counting up for stat with ID " + statistic.getId() + " name " + statistic.getName() + " and date " + statistic.getFromDate());

        dailyHits.setHits(dailyHits.getHits() + 1);
      } else {
        System.out.println("ERROR");
      }
      System.out.println("\n\n");
    }
    dailyHitList.add(dailyHits);
    System.out.println("Done");

    StringBuilder json = new StringBuilder();
    json.append("[{data:[");
    for (int i = 0; i < dailyHitList.size(); i++) {
      Statistic statistic = dailyHitList.get(i);

      json.append("[");

      json.append(statistic.getFromDate().getTime());
      json.append(",").append(statistic.getHits());

      json.append("]");

      if (i < (dailyHitList.size() - 1)) {
        json.append(",\n");
      }
    }
    json.append("],");
    json.append("label: 'Overall Hits'");
    json.append("}");
    json.append(",");

    json.append("{data:[");
    for (int i = 0; i < dailyHitList.size(); i++) {
      json.append("[");

      json.append(dailyHitList.get(i).getFromDate().getTime());
      json.append(",").append(((int) (Math.random() * 55)));

      json.append("]");
      if (i < (dailyHitList.size() - 1)) {
        json.append(",\n");
      }
    }
    json.append("],");
    json.append("label: 'Test Hits'");
    json.append("}");

    json.append("]");

    return json.toString();
  }

  private static Date cutTillDay(Date date) {
    System.out.println("given Date: " + date);
    Date newDate = new Date(date.getYear(), date.getMonth(), date.getDate());
    System.out.println("resulting Date: " + newDate);
    return newDate;
  }

  private static Date cutTillHour(Date date) {
    System.out.println("given Date: " + date);
    Date newDate = new Date(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), 0);
    System.out.println("resulting Date: " + newDate);
    return newDate;
  }

  private static Date cutTillMinute(Date date) {
    System.out.println("given Date: " + date);
    Date newDate = new Date(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), 0);
    System.out.println("resulting Date: " + newDate);
    return newDate;
  }
}
