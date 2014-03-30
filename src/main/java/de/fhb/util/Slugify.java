package de.fhb.util;

import java.text.Normalizer;
import org.apache.commons.lang3.StringUtils;

/**
 * https://github.com/slugify
 *
 * @author Benny
 */
public class Slugify {

  public static String slugify(String input) {
    String ret = StringUtils.trim(input);
    if (StringUtils.isBlank(input)) {
      return "";
    }

    // Replace German umlauts
    ret = ret.replaceAll("[üÜ]", "ue").replaceAll("[äÄ]", "ae").replaceAll("[öÖ]", "oe").replaceAll("ß", "ss");
    // Replace special characters
    ret = ret.replaceAll("\\+", "plus").replaceAll("\\#", " sharp").replaceAll("-", " ");
    // Normalize string and return it
    ret = normalize(ret);
    ret = removeDuplicateWhiteSpaces(ret);
    return ret.replace(" ", "-").replace("plus", "+").toLowerCase();
  }

  private static String normalize(String input) {
    String ret = StringUtils.trim(input);
    if (StringUtils.isBlank(ret)) {
      return "";
    }

    ret = ret.replace("ß", "ss");
    return Normalizer.normalize(ret, Normalizer.Form.NFD)
            .replaceAll("[^\\p{ASCII}]", "")
            .replaceAll("[^a-zA-Z0-9 ]", "");
  }

  private static String removeDuplicateWhiteSpaces(String input) {
    String ret = StringUtils.trim(input);
    if (StringUtils.isBlank(ret)) {
      return "";
    }

    return ret.replaceAll("\\s+", " ");
  }
}