package com.welovecoding.tutorial.data.playlist;

import com.welovecoding.tutorial.data.base.BaseService;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class PlaylistService extends BaseService<Playlist, PlaylistRepository> {

  @EJB private PlaylistRepository repository;
  @EJB private OwnCacheServerService cacheService;

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

  public Playlist findByCode(String code) {
    return repository.findByCode(code);
  }

  public Playlist findInCategory(Long categoryid, Long playlistid) {
    return repository.findInCategory(categoryid, playlistid);
  }

  public Playlist findByCodeDetached(String code) {
    Playlist playlist = this.findByCode(code);
    repository.em.detach(playlist);
    return playlist;
  }

  public Playlist findByCategoryAndSlug(long categoryid, String slug) {
    return repository.findByCategoryAndSlug(categoryid, slug);
  }

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(Arrays.asList(
            com.welovecoding.tutorial.api.v1.dto.PlaylistDTO.class.getName(),
            com.welovecoding.tutorial.api.v2.dto.PlaylistDTO.class.getName()
    ));
  }
}
