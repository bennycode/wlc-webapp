package com.welovecoding.service;

import com.welovecoding.entities.Playlist;
import com.welovecoding.repository.PlaylistRepository;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PlaylistService extends BaseService<Playlist, PlaylistRepository> {

  @EJB
  private PlaylistRepository repository;
  @EJB
  private OwnCacheServerService cacheService;

  public PlaylistService() {
    super(Playlist.class);
  }

  @Override
  protected PlaylistRepository getRepository() {
    return repository;
  }

  @Override
  protected OwnCacheServerService getCache() {
    return cacheService;
  }

  public Playlist getPlaylistByCode(String code) {
    return repository.findByCode(code);
  }

  public List<Playlist> findAllInCategory(Long categoryid) {
    return repository.findAllInCategory(categoryid);
  }

  public Playlist findInCategory(Long categoryid, Long playlistid) {
    return repository.findInCategory(categoryid, playlistid);
  }

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(Arrays.asList(
            com.welovecoding.rest.v1.dto.PlaylistDTO.class.getName(),
            com.welovecoding.rest.v2.dto.PlaylistDTO.class.getName()
    ));
  }
}
