package com.welovecoding;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Debugger {

  private static final Logger LOG = Logger.getLogger(Debugger.class.getName());

  public static void logProperties(Object object) {
    Class<?> aClass = object.getClass();
    Field[] declaredFields = aClass.getDeclaredFields();
    Map<String, String> logEntries = new HashMap<>();

    for (Field field : declaredFields) {
      field.setAccessible(true);

      Object[] arguments;
      try {
        arguments = new Object[]{
          field.getName(),
          field.getType().getSimpleName(),
          String.valueOf(field.get(object))
        };
      } catch (IllegalArgumentException | IllegalAccessException ex) {
        arguments = new Object[]{
          field.getName(),
          field.getType().getSimpleName(),
          ""
        };
      }

      String template = "- Property: {0} (\"{2}\", {1})";
      String logMessage = System.getProperty("line.separator")
              + MessageFormat.format(template, arguments);

      logEntries.put(field.getName(), logMessage);
    }

    SortedSet<String> sortedLog = new TreeSet<>(logEntries.keySet());

    StringBuilder sb = new StringBuilder("Class properties:");

    Iterator<String> it = sortedLog.iterator();
    while (it.hasNext()) {
      String key = it.next();
      sb.append(logEntries.get(key));
    }

    LOG.log(Level.INFO, sb.toString());
  }
}
