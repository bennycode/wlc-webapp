package com.welovecoding.web.debug;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Benny Neugebauer (bn@bennyn.de)
 */
public class MySQLDaemon implements Runnable {

  @Override
  /**
   * Runs a MySQL daemon process in the background.
   * <p>
   * @see http://stackoverflow.com/a/17402798/451634
   */
  public void run() {
    String command = "D:\\dev\\env\\xampp\\mysql\\bin\\mysqld.exe";
    ProcessBuilder pb = new ProcessBuilder(command);
    pb.redirectErrorStream(true);
    try {
      pb.start();
    } catch (IOException ex) {
      Logger.getLogger(MySQLDaemon.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
