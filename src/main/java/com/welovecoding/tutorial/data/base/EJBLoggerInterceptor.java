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
 * @author Michael Koppen
 */
public class EJBLoggerInterceptor {

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
    Logger LOG = Logger.getLogger(context.getMethod().getDeclaringClass().getName());
    LOG.setLevel(Level.WARNING);

//    System.out.println("Loggername: " + LOG.getName());
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
