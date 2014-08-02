package com.welovecoding.tutorial.data.playlist;

import com.welovecoding.tutorial.data.base.BaseService;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

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

  /**
   * Needed for YouTube importer.
   *
   * @param code Playlist ID, Example: PLpyrjJvJ7GJ4i-2v0kVohE1cWJs12TjDF
   * @return Playlist
   */
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public Playlist getDetachedPlaylistByCode(String code) {
    return repository.findByCode(code);
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

  public Playlist findByCategoryAndSlug(Long categoryid, String slug) {
    return repository.findByCategoryAndSlug(categoryid, slug);
  }

  public List<Playlist> findAllInCategory(Long categoryid) {
    return repository.findAllInCategory(categoryid);
  }

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(Arrays.asList(
            com.welovecoding.tutorial.api.v1.dto.PlaylistDTO.class.getName(),
            com.welovecoding.tutorial.api.v2.dto.PlaylistDTO.class.getName()
    ));
  }
}
