package com.welovecoding.tutorial.data.video;

import com.welovecoding.tutorial.data.base.BaseService;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
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

  //TODO Why NOT_SUPPORTED ?
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public Video getDetachedVideoByCode(String code) {
    return repository.findByCode(code);
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
