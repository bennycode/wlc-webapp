package de.fhb.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
@JsonTypeName("language")
public class Language implements Serializable {

  private String lang;

  public Language() {
  }

  public Language(String lang) {
    this.lang = lang;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

}
