package de.fhb.service;

import de.fhb.entities.Playlist;
import de.fhb.repository.PlaylistRepository;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.ArrayList;
import java.util.List;
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
    return repository.getByCode(code);
  }

  public List<Playlist> getAllByCategory(long id) {
    // TODO Implement getAllPlaylistsByCategory(long)
    // Never return null. instead return an empty List
    return new ArrayList<>();
  }

  public Playlist findInCategory(Long categoryid, Long playlistid) {
    return repository.findInCategory(categoryid, playlistid);
  }
}
