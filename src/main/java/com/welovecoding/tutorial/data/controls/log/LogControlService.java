package com.welovecoding.tutorial.data.controls.log;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Michael Koppen
 */
@Singleton
@Startup
public class LogControlService {

  public LogControlService() {
    System.out.println("######### LogControlService Constructor");
    // Service Loggers
    Logger.getLogger(BaseService.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(AuthorService.class.getName())
            .setLevel(Level.INFO);
    Logger.getLogger(CategoryService.class.getName())
            .setLevel(Level.INFO);
    Logger.getLogger(PlaylistService.class.getName())
            .setLevel(Level.INFO);
    Logger.getLogger(UserService.class.getName())
            .setLevel(Level.INFO);
    Logger.getLogger(VideoService.class.getName())
            .setLevel(Level.INFO);
    Logger.getLogger(YouTubeService.class.getName())
            .setLevel(Level.INFO);

    // Repository Loggers
    Logger.getLogger(BaseRepository.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(AuthorRepository.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(CategoryRepository.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(PlaylistRepository.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(UserRepository.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(VideoRepository.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(YouTubeRepository.class.getName())
            .setLevel(Level.WARNING);

    System.out.println("Setting Cache Loggers to Level.WARNING");
    // Cache Loggers
    Logger.getLogger(OwnCacheService.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(OwnSimpleCacheLoggingHook.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(OwnCacheServerService.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(OwnCacheListener.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(de.yser.ownsimplecache.util.jaxrs.KeyGenerator.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(de.yser.ownsimplecache.util.ejb.KeyGenerator.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(EntityTagGenerator.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(de.yser.ownsimplecache.util.jaxrs.CacheInterceptor.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(de.yser.ownsimplecache.util.ejb.CacheInterceptor.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(ClearCachesInterceptor.class.getName())
            .setLevel(Level.WARNING);
    Logger.getLogger(ClearCacheInterceptor.class.getName())
            .setLevel(Level.WARNING);
  }

}
