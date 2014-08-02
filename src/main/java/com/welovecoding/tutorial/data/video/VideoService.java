package com.welovecoding.tutorial.data.video;

import com.welovecoding.tutorial.data.base.BaseService;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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

  public Video findByCode(String code) {
    return repository.findByCode(code);
  }

  public Video findByCodeDetached(String code) {
    Video video = this.findByCode(code);

    if (video != null) {
      repository.em.detach(video);
    }

    return video;
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

  public Video findByPlaylistAndSlug(long playlistid, String slug) {
    return repository.findByPlaylistAndSlug(playlistid, slug);
  }

  public List<Video> findAllInPlaylist(Long playlistid) {
    return repository.findAllInPlaylist(playlistid);
  }

  public List<Video> findAllInCategoryAndPlaylist(Long categoryid, Long playlistid) {
    return repository.findAllInCategoryAndPlaylist(categoryid, playlistid);
  }

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(Arrays.asList(
            com.welovecoding.tutorial.api.v1.dto.VideoDTO.class.getName(),
            com.welovecoding.tutorial.api.v2.dto.VideoDTO.class.getName()
    ));
  }

}
