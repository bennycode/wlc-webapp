package de.fhb.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoogleCredentials {

  private String clientId;
  private String clientSecret;

  public GoogleCredentials() {

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream stream = classLoader.getResourceAsStream("production.properties");

    if (stream != null) {
      try {
        Properties p = new Properties();
        p.load(stream);

        this.clientId = p.getProperty("GOOGLE_CLIENT_ID");
        this.clientSecret = p.getProperty("GOOGLE_CLIENT_SECRET");

      } catch (IOException ex) {
        Logger.getLogger(GoogleCredentials.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

}
