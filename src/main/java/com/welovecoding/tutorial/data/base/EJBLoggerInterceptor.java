/*
 * Copyright (C) 2013 Michael Koppen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.welovecoding.tutorial.data.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * This Interceptor should be invoked in every session-bean-service-class and is
 * responsible for logging genaral information about actual classname methodname
 * and params.
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
public class EJBLoggerInterceptor {

  private static Logger LOG = Logger.getLogger(EJBLoggerInterceptor.class.getName());

  public EJBLoggerInterceptor() {
  }

  /**
   * logging of genaral information about actual classname methodname and
   * params.
   *
   * @param context
   * @return context proceed
   * @throws Exception
   */
  @AroundInvoke
  public Object logCall(InvocationContext context) throws Exception {
    Logger.getLogger(context.getMethod().getDeclaringClass().getName());
    // EJB will not be accessable if the Interceptor is called from outside of the backend-module
    StringBuilder log = new StringBuilder("---------------------------------------------------------\n");

    log.append(" + Class: ").append(getPureClassName(context.getMethod().getDeclaringClass())).append("\n");
    log.append(" -    Method: ").append(context.getMethod().getName()).append("\n");

    if (context.getParameters() != null) {
      Annotation[][] annos = context.getMethod().getParameterAnnotations();
      Object[] params = context.getParameters();
      for (int i = 0; i < annos.length; i++) {

        for (int j = 0; j < annos[i].length; j++) {
          Annotation annotation = annos[i][j];
          log.append(" -       Annotation for Param ").append(i + 1).append(": ").append(annotation.annotationType()).append("\n");
        }

        if (params[i] != null) {
          log.append(" -       Param ").append(i + 1).append(": (").append(getPureClassName(params[i].getClass())).append(") ").append(params[i]).append("\n");
        } else {
          log.append(" -       Param ").append(i + 1).append(": () ").append(params[i]).append("\n");
        }
      }
    }

    Object retVal = context.proceed();

    log.append(" -       ReturnValue ").append(": ").append(retVal).append("\n");
//    if (retVal != null) {
//      Field[] retValFields = retVal.getClass().getDeclaredFields();
//      for (Field field : retValFields) {
//        System.out.println("Fieldname: " + field.getName() + " Fieldtype: " + field.getType());
//        Object retVal2 = invokeGetter(retVal, field);
//        System.out.println("FieldValue: " + retVal2);
//      }
//    } else {
//      System.out.println("skipping values");
//    }

    LOG.log(Level.INFO, log.toString());

    return retVal;
  }

  /**
   * seperates the packagename from the classname and returns the classname.
   *
   * @param klasse
   * @return String
   */
  private String getPureClassName(Class klasse) {
    String temp = "";

    String classNameWithPackage = klasse.getName();
    String packetname = klasse.getPackage().getName();

    temp = packetname.replaceAll("package ", "");

    temp = classNameWithPackage.replaceAll(temp + ".", "");

    return temp;
  }

  private String capitalize(String line) {
    return Character.toUpperCase(line.charAt(0)) + line.substring(1);
  }

  private Object invokeGetter(Object invokeInObject, Field field) {
    Class<?>[] emptyParamObjects = new Class<?>[]{};
    Object[] emptyParams = new Object[]{};
    try {
      return invokeInObject.getClass().getDeclaredMethod("get" + capitalize(field.getName()), emptyParamObjects).invoke(invokeInObject, emptyParams);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//      Logger.getLogger(EJBLoggerInterceptor.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;

  }
}
