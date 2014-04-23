package de.fhb.entities;

/**
 * Example: "en" (English), "ja" (Japanese), "kok" (Konkani)
 *
 * @see http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
 * @see http://docs.oracle.com/javase/7/docs/api/java/util/Locale.html
 */
public enum LanguageCode {

  EN, en, DE, de;

  @Override
  public String toString() {
    switch (this) {
      case DE:
      case de:
        return "German";
      default:
        return "English";
    }
  }

}
