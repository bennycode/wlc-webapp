package com.welovecoding.tutorial.data.video;

import com.welovecoding.tutorial.data.base.BaseService;
import com.welovecoding.tutorial.data.base.EJBLoggerInterceptor;
import com.welovecoding.tutorial.data.monitor.MonitorInterceptor;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({EJBLoggerInterceptor.class, MonitorInterceptor.class})
public class VideoService extends BaseService<Video, VideoRepository> {

  @EJB private VideoRepository repository;
  @EJB private OwnCacheServerService cacheService;

  public VideoService() {
    super(Video.class);
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
    return repository.findByCode(code);
  }

  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public Video getDetachedVideoByCode(String code) {
    return repository.findByCode(code);
  }

  public List<Video> getVideosByPlaylistId(Long playlistId) {
    return repository.getVideosByPlaylistId(playlistId);
  }

  public Video findInCategory(Long categoryid, Long videoid) {
    return repository.findInCategory(categoryid, videoid);
  }

  public Video findInCategoryAndPlaylist(Long categoryid, Long playlistid, Long videoid) {
    return repository.findInCategoryAndPlaylist(categoryid, playlistid, videoid);
  }

  public Video findInPlaylist(Long playlistid, Long videoid) {
    return repository.findInPlaylist(playlistid, videoid);
  }

  public Video getByPlaylistAndSlug(long playlistid, String slug) {
    return repository.getByPlaylistAndSlug(playlistid, slug);
  }

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(Arrays.asList(
            com.welovecoding.tutorial.api.v1.dto.VideoDTO.class.getName(),
            com.welovecoding.tutorial.api.v2.dto.VideoDTO.class.getName()
    ));
  }

}
