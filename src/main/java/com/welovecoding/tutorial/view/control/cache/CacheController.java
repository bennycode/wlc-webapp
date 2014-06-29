package com.welovecoding.tutorial.view.control.cache;

import com.welovecoding.tutorial.data.control.cache.CacheControlService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen
 */
@Named
@RequestScoped
public class CacheController {

  @EJB
  private CacheControlService cacheService;

  public void clearRestCache() {
    cacheService.clearRestCache();
  }

  public void clearDatabaseCache() {
    cacheService.clearDatabaseCache();
  }
}
