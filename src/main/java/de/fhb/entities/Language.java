package de.fhb.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
@JsonTypeName("language")
/**
 * ISO 639-1: two-letter codes
 *
 * @see http://en.wikipedia.org/wiki/ISO_639-1
 */
public class Language implements Serializable {

  public class LanguageCode {

    static public final String GERMAN = "de";
    static public final String ENGLISH = "en";
  }

  private String languageCode;

  public Language() {
  }

  public Language(String languageCode) {
    this.languageCode = languageCode;
  }

  public String getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }

  @Override
  public String toString() {
    return this.getLanguageCode();
  }

}
