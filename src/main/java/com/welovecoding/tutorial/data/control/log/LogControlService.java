package com.welovecoding.tutorial.data.control.log;

import com.welovecoding.tutorial.data.author.AuthorRepository;
import com.welovecoding.tutorial.data.author.AuthorService;
import com.welovecoding.tutorial.data.base.BaseRepository;
import com.welovecoding.tutorial.data.base.BaseService;
import com.welovecoding.tutorial.data.category.CategoryRepository;
import com.welovecoding.tutorial.data.category.CategoryService;
import com.welovecoding.tutorial.data.playlist.PlaylistRepository;
import com.welovecoding.tutorial.data.playlist.PlaylistService;
import com.welovecoding.tutorial.data.user.UserRepository;
import com.welovecoding.tutorial.data.user.UserService;
import com.welovecoding.tutorial.data.video.VideoRepository;
import com.welovecoding.tutorial.data.video.VideoService;
import com.welovecoding.tutorial.data.youtube.YouTubeRepository;
import com.welovecoding.tutorial.data.youtube.YouTubeService;
import de.yser.ownsimplecache.OwnCacheListener;
import de.yser.ownsimplecache.OwnCacheServerService;
import de.yser.ownsimplecache.OwnCacheService;
import de.yser.ownsimplecache.util.clear.ClearCacheInterceptor;
import de.yser.ownsimplecache.util.clear.ClearCachesInterceptor;
import de.yser.ownsimplecache.util.hook.logging.OwnSimpleCacheLoggingHook;
import de.yser.ownsimplecache.util.jaxrs.EntityTagGenerator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Michael Koppen
 */
@Singleton
@Startup
//@DependsOn(value = "otherSingletonStartupEJB")
public class LogControlService {

  public static final String SERVICE_LOGGERS = "service";
  public static final String REPOSITORY_LOGGERS = "repository";
  public static final String CACHE_LOGGERS = "cache";
  private static final Logger ROOT_LOGGER = Logger.getLogger("");
  private static final LogHandler ROOT_LOG_HANDLER = new LogHandler();
  private static Map<String, Map> loggerTypes;

  public LogControlService() {
    System.out.println("LogControlService()");
    loggerTypes = new HashMap<>();
  }

  @PostConstruct
  private void init() {
    System.out.println("LogControlService#init()");
    Map<String, Logger> serviceLoggers = new HashMap<>();
    Map<String, Logger> repositoryLoggers = new HashMap<>();
    Map<String, Logger> cacheLoggers = new HashMap<>();
    loggerTypes.put(SERVICE_LOGGERS, serviceLoggers);
    loggerTypes.put(REPOSITORY_LOGGERS, repositoryLoggers);
    loggerTypes.put(CACHE_LOGGERS, cacheLoggers);

    Logger tempLogger;
    // Service Loggers
    tempLogger = Logger.getLogger(BaseService.class.getName());
    tempLogger.setLevel(Level.WARNING);
    serviceLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(AuthorService.class.getName());
    tempLogger.setLevel(Level.WARNING);
    serviceLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(CategoryService.class.getName());
    tempLogger.setLevel(Level.WARNING);
    serviceLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(PlaylistService.class.getName());
    tempLogger.setLevel(Level.WARNING);
    serviceLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(UserService.class.getName());
    tempLogger.setLevel(Level.WARNING);
    serviceLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(VideoService.class.getName());
    tempLogger.setLevel(Level.WARNING);
    serviceLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(YouTubeService.class.getName());
    tempLogger.setLevel(Level.WARNING);
    serviceLoggers.put(tempLogger.getName(), tempLogger);

    // Repository Loggers
    tempLogger = Logger.getLogger(BaseRepository.class.getName());
    tempLogger.setLevel(Level.WARNING);
    repositoryLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(AuthorRepository.class.getName());
    tempLogger.setLevel(Level.WARNING);
    repositoryLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(CategoryRepository.class.getName());
    tempLogger.setLevel(Level.WARNING);
    repositoryLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(PlaylistRepository.class.getName());
    tempLogger.setLevel(Level.WARNING);
    repositoryLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(UserRepository.class.getName());
    tempLogger.setLevel(Level.WARNING);
    repositoryLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(VideoRepository.class.getName());
    tempLogger.setLevel(Level.WARNING);
    repositoryLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(YouTubeRepository.class.getName());
    tempLogger.setLevel(Level.WARNING);
    repositoryLoggers.put(tempLogger.getName(), tempLogger);

    // Cache Loggers
    tempLogger = Logger.getLogger(OwnCacheService.class.getName());
    tempLogger.setLevel(Level.WARNING);
    cacheLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(OwnSimpleCacheLoggingHook.class.getName());
    tempLogger.setLevel(Level.WARNING);
    cacheLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(OwnCacheServerService.class.getName());
    tempLogger.setLevel(Level.WARNING);
    cacheLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(OwnCacheListener.class.getName());
    tempLogger.setLevel(Level.WARNING);
    cacheLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(de.yser.ownsimplecache.util.jaxrs.KeyGenerator.class.getName());
    tempLogger.setLevel(Level.WARNING);
    cacheLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(de.yser.ownsimplecache.util.ejb.KeyGenerator.class.getName());
    tempLogger.setLevel(Level.WARNING);
    cacheLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(EntityTagGenerator.class.getName());
    tempLogger.setLevel(Level.WARNING);
    cacheLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(de.yser.ownsimplecache.util.jaxrs.CacheInterceptor.class.getName());
    tempLogger.setLevel(Level.WARNING);
    cacheLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(de.yser.ownsimplecache.util.ejb.CacheInterceptor.class.getName());
    tempLogger.setLevel(Level.WARNING);
    cacheLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(ClearCachesInterceptor.class.getName());
    tempLogger.setLevel(Level.WARNING);
    cacheLoggers.put(tempLogger.getName(), tempLogger);

    tempLogger = Logger.getLogger(ClearCacheInterceptor.class.getName());
    tempLogger.setLevel(Level.WARNING);
    cacheLoggers.put(tempLogger.getName(), tempLogger);

    addLogHandler();
  }

  private void addLogHandler() {
    ROOT_LOGGER.addHandler(ROOT_LOG_HANDLER);
  }

  @PreDestroy
  private void destroy() {
    System.out.println("LogControlService#destroy()");
  }

  public static Map<String, Map> getLoggerTypes() {
    return loggerTypes;
  }

  public static void setLoggerTypes(Map<String, Map> loggerTypes) {
    LogControlService.loggerTypes = loggerTypes;
  }

  public Iterator<String> getLogs() {
    return ROOT_LOG_HANDLER.getLogMessages();
  }

  public Iterator<String> getDescendingLogs() {
    return ROOT_LOG_HANDLER.getDescendingLogMessages();
  }

}
