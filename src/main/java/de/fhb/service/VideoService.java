package de.fhb.service;

import de.fhb.entities.Video;
import de.fhb.logging.interceptor.EJBLoggerInterceptor;
import de.fhb.repository.VideoRepository;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({EJBLoggerInterceptor.class})
public class VideoService extends BaseService<Video, VideoRepository> {

  @EJB
  private VideoRepository repository;
  private OwnCacheServerService cacheService;

  public VideoService() {
    super(Video.class);
    cacheService = OwnCacheServerService.getInstance();
  }

  @Override
  protected VideoRepository getRepository() {
    return repository;
  }

  @Override
  protected OwnCacheServerService getCache() {
    return cacheService;
  }

  public Video getVideoByCode(String code) {
    return repository.getVideoByCode(code);
  }

  public List<Video> getAllVideosByPlaylist(long id) {
    // TODO Implement getAllVideosByPlaylist(long)
    // Never return null. instead return an empty List
    return new ArrayList<Video>();
  }

}
