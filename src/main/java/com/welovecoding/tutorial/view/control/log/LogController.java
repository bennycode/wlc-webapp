package com.welovecoding.tutorial.view.control.log;

import com.welovecoding.tutorial.data.control.log.LogControlService;
import java.util.Iterator;
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
    if (latestLog == null) {
      StringBuilder log = new StringBuilder();
      Iterator<String> logs = logService.getDescendingLogs();
      while (logs.hasNext()) {
        log.append(logs.next()).append("\n");
      }
      latestLog = log.toString();
    }
  }

  public String getLatestLog() {
    loadLatestLog();
    return latestLog;
  }

  public void setLatestLog(String latestLog) {
    this.latestLog = latestLog;
  }

}
