package com.welovecoding.service;

import com.welovecoding.entities.Video;
import com.welovecoding.repository.VideoRepository;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class VideoService extends BaseService<Video, VideoRepository> {

  @EJB
  private VideoRepository repository;
  @EJB
  private OwnCacheServerService cacheService;

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

  public List<Video> getAllInPlaylist(Long playlistid) {
    // TODO Implement getAllVideosByPlaylist(long)
    // Never return null. instead return an empty List
    return new ArrayList<Video>();
  }

  public List<Video> getAllInCategory(Long playlistid) {
    // TODO Implement getAllVideosByPlaylist(long)
    // Never return null. instead return an empty List
    return new ArrayList<>();
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

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(Arrays.asList(
            com.welovecoding.rest.v1.dto.VideoDTO.class.getName(),
            com.welovecoding.rest.v2.dto.VideoDTO.class.getName()
    ));
  }

}
