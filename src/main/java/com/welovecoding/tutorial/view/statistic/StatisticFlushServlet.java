package com.welovecoding.tutorial.view.statistic;

import com.welovecoding.tutorial.data.statistic.StatisticService;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Michael Koppen
 */
@WebListener
public class StatisticFlushServlet implements ServletContextListener {

  @EJB
  private StatisticService statService;

  @Override
  public void contextInitialized(ServletContextEvent event) {
    // Do your job here during webapp startup.
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    statService.flush();
  }

}
