package com.welovecoding.tutorial.view.statistic;

import com.welovecoding.tutorial.data.statistic.StatisticService;
import java.util.logging.Level;
import java.util.logging.Logger;
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

  private static final Logger LOG = Logger.getLogger(StatisticFlushServlet.class.getName());

  @EJB
  private StatisticService statService;

  @Override
  public void contextInitialized(ServletContextEvent event) {
    // Do your job here during webapp startup.
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    try {
      statService.flush();
    } catch (Exception e) {
      LOG.log(Level.WARNING, "Could not flush statistics!", e.getMessage());
      LOG.log(Level.INFO, "FlushException: ", e);
    }

  }

}
