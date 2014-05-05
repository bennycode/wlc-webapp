package com.welovecoding.tutorial.data.playlist;

import com.welovecoding.tutorial.data.base.BaseService;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.data.playlist.PlaylistRepository;
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
            com.welovecoding.tutorial.api.v1.dto.PlaylistDTO.class.getName(),
            com.welovecoding.tutorial.api.v2.dto.PlaylistDTO.class.getName()
    ));
  }
}
