package com.welovecoding.tutorial.data.monitor;

import de.yser.ownsimplecache.util.hook.Hook;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * NOTE: Monitoring deactivated for now.
 *
 * @author Michael Koppen
 */
public class MonitorCacheHook implements Hook {

  private MonitorService monitorService;
  private static final Logger LOG = Logger.getLogger(MonitorCacheHook.class.getName());

  public MonitorCacheHook() {

  }

  private void injectServices() {
    if (monitorService == null) {
      try {
        //possible versions of JNDI lookup
        monitorService = (MonitorService) new InitialContext().lookup("java:module/MonitorService");
//        monitorService = (MonitorService) new InitialContext().lookup("java:global/_We_Love_Coding__webapp_/MonitorService!com.welovecoding.tutorial.data.monitor.MonitorService");
//        monitorService = (MonitorService) new InitialContext().lookup("java:app/_We_Love_Coding__webapp_/MonitorService");
//        monitorService = (MonitorService) new InitialContext().lookup("java:global/_We_Love_Coding__webapp_/MonitorService");

        System.out.println("Successfully injected MonitorService!");
      } catch (NamingException ex) {
        System.out.println("Iinjection of  MonitorService failed!");
      }
    }

  }

  @Override
  public void willCache(String objectOfClass, String withKey, String andValue) {
//    LOG.log(Level.INFO, "willCache object of class \"{0}\" with key \"{1}\" and value \"{2}\".", new Object[]{objectOfClass, withKey, andValue});
//    injectServices();
  }

  @Override
  public void didCache(String objectOfClass, String withKey, String andValue) {
//    LOG.log(Level.INFO, "didCache object of class \"{0}\" with key \"{1}\" and value \"{2}\".", new Object[]{objectOfClass, withKey, andValue});
//    injectServices();
  }

  @Override
  public void willGet(String objectOfClass, String withKey) {
//    LOG.log(Level.INFO, "willGet object of class \"{0}\" with key \"{1}\".", new Object[]{objectOfClass, withKey});
//    injectServices();
  }

  @Override
  public void didGet(String objectOfClass, String withKey) {
//    LOG.log(Level.INFO, "didGet object of class \"{0}\" with key \"{1}\".", new Object[]{objectOfClass, withKey});
//    injectServices();
//    if (monitorService != null) {
//      Invocation invocation = new Invocation();
//      invocation.setName(withKey);
//      monitorService.create(invocation);
//    }

  }

  @Override
  public void willInvalidateCache(String ofClass, String withHint) {
//    LOG.log(Level.INFO, "willInvalidateCache of class \"{0}\" with hint \"{1}\".", new Object[]{ofClass, withHint});
//    injectServices();
  }

  @Override
  public void didInvalidateCache(String ofClass, String withHint) {
//    LOG.log(Level.INFO, "didInvalidateCache of class \"{0}\" with hint \"{1}\".", new Object[]{ofClass, withHint});
//    injectServices();
  }

  @Override
  public void willInvalidateAllCaches() {
//    LOG.log(Level.INFO, "willInvalidateAllCaches.");
//    injectServices();
  }

  @Override
  public void didInvalidateAllCaches() {
//    LOG.log(Level.INFO, "didInvalidateAllCaches.");
//    injectServices();
  }

  @Override
  public void willCreateCache(String name, String genericTypeHint, long expiringTime, TimeUnit unit) {
//    LOG.log(Level.INFO, "willCreateCache \"{0}\" with hint \"{1}\" and expiring time \"{2} {3}\".", new Object[]{name, genericTypeHint, expiringTime, unit.name()});
//    injectServices();
  }

  @Override
  public void didCreateCache(String name, String genericTypeHint, long expiringTime, TimeUnit unit) {
//    LOG.log(Level.INFO, "didCreateCache \"{0}\" with hint \"{1}\" and expiring time \"{2} {3}\".", new Object[]{name, genericTypeHint, expiringTime, unit.name()});
//    injectServices();
  }

}
