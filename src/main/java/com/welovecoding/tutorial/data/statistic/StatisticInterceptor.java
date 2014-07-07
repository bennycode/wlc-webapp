package com.welovecoding.tutorial.data.statistic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.ws.rs.PathParam;

/**
 * NOTE: Monitoring deactivated for now.
 *
 * @author Michael Koppen
 */
@Stateless
public class StatisticInterceptor {

  private static Logger LOG = Logger.getLogger(StatisticInterceptor.class.getName());

  @EJB
  private StatisticService statisticService;

  public StatisticInterceptor() {
  }

  /**
   *
   *
   * @param context
   * @return context proceed
   * @throws Exception
   */
  @AroundInvoke
  public Object logCall(InvocationContext context) throws Exception {

    Object retVal = context.proceed();

//    Invocation invocation = new Invocation();
//    invocation.setName(generateKey(context.getMethod(), context.getParameters()));
//    statisticService.create(invocation);
    return retVal;
  }

  private String generateKey(Method method, Object[] params) {

    String generatedKey = method.getDeclaringClass().getSimpleName() + "-" + method.getName();
    LOG.log(Level.INFO, "+Generating monitorKey");
    LOG.log(Level.FINEST, "+Parameter Annotations:");
    Annotation[][] parameterAnnotations = method.getParameterAnnotations();
    Class[] parameterTypes = method.getParameterTypes();

    int i = 0;
    for (Annotation[] annotations : parameterAnnotations) {
      Class parameterType = parameterTypes[i];
      for (Annotation annotation : annotations) {
        if (annotation instanceof PathParam) {
          LOG.log(Level.FINEST, "-pType: {0}", parameterType.getName());
          LOG.log(Level.FINEST, "-pValue: {0}", params[i]);
          LOG.log(Level.FINEST, "-aType: {0}", annotation.annotationType().getName());
          LOG.log(Level.FINEST, "-aValue: {0}", ((PathParam) annotation).value());
          generatedKey = generatedKey + "-" + ((PathParam) annotation).value() + "-" + params[i];
        }
      }
      i++;
    }
    LOG.log(Level.INFO, "-Generated key is {0}", new Object[]{generatedKey});
    return generatedKey;
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
      try {
        return invokeInObject.getClass().getDeclaredMethod("is" + capitalize(field.getName()), emptyParamObjects).invoke(invokeInObject, emptyParams);
      } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex2) {
      }
    }
    return null;

  }
}
