package de.fhb.util;

public class StringUtils {

  public static String capitalize(String line) {
    return Character.toUpperCase(line.charAt(0)) + line.substring(1);
  }

  public static String lowerFirstChar(String line) {
    return Character.toLowerCase(line.charAt(0)) + line.substring(1);
  }

  public static String getControllerBeanName(Object object) {
    return getControllerBeanName(object.getClass().getSimpleName());
  }

  public static String getControllerBeanName(String type) {
    return lowerFirstChar(type) + "Controller";
  }

  /**
   * @param value Category[id=1]
   * @return String categoryController
   */
  public static String getControllerBeanNameByStringValue(String value) {
    String className = value.substring(0, value.indexOf("["));
    return getControllerBeanName(className);
  }

  /**
   * @param value Category[id=5]
   * @return Long 5
   */
  public static Long getIdByStringValue(String value) {
    String id = value.substring(value.indexOf("id=") + 3, value.indexOf("]"));
    return Long.valueOf(id);
  }
}
