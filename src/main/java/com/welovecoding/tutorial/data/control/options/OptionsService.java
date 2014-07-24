package com.welovecoding.tutorial.data.control.options;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Michael Koppen
 */
@Singleton
@Startup
public class OptionsService {

  private static final Logger LOG = Logger.getLogger(OptionsService.class.getName());

  public static Properties SYSTEM_PROPERTIES;

  public OptionsService() {

  }

  @PostConstruct
  private void init() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream stream = classLoader.getResourceAsStream("production.properties");
    if (stream != null) {
      try {
        Properties p = new Properties();
        p.load(stream);
        SYSTEM_PROPERTIES = p;
      } catch (IOException ex) {
        LOG.log(Level.SEVERE, "Could not load system properties!", ex);
      }
    }
  }

  public class OptionKeys {

    public static final String GOOGLE_CLIENT_ID = "GOOGLE_CLIENT_ID";
    public static final String GOOGLE_CLIENT_SECRET = "GOOGLE_CLIENT_SECRET";
    public static final String PROJECT_STAGE = "PROJECT_STAGE";
  }

}
