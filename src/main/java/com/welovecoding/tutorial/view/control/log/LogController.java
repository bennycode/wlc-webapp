package com.welovecoding.tutorial.view.control.log;

import com.welovecoding.tutorial.data.control.log.LogControlService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen
 */
@Named
@RequestScoped
public class LogController {

  @EJB
  private LogControlService logService;
  private String latestLog;

  private void loadLatestLog() {
    StringBuilder log = new StringBuilder();
    for (String logLine : logService.getLogs()) {
      log.append(logLine).append("\n");
    }
    latestLog = log.toString();
  }

  public String getLatestLog() {
    if (latestLog == null) {
      loadLatestLog();
    }
    return latestLog;
  }

  public void setLatestLog(String latestLog) {
    this.latestLog = latestLog;
  }

}
