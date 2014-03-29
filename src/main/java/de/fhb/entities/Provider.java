package de.fhb.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Provider implements Serializable {

  public class ProviderName {

    static public final String YOUTUBE = "YouTube";
    static public final String VIMEO = "Vimeo";
    static public final String CHANNEL9 = "Channel9";
  }

  private String providerName;

  public Provider() {
  }

  public Provider(String providerName) {
    this.providerName = providerName;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

}
