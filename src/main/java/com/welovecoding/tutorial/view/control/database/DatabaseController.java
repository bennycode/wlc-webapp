package com.welovecoding.tutorial.view.control.database;

import com.welovecoding.tutorial.data.control.database.DatabaseControlService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen
 */
@Named
@SessionScoped
public class DatabaseController implements Serializable {

  @EJB
  private DatabaseControlService databaseService;

  private String dump = "";

  public void loadDatabaseDump() {
    if (dump == null) {
      dump = databaseService.dumpDatabase();
    }
  }

  public String reload() {
    dump = null;
    return "";
  }

  public String getDump() {
    loadDatabaseDump();
    return dump;
  }

  public void setDump(String dump) {
    this.dump = dump;
  }

}
