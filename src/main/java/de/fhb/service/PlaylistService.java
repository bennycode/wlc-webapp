package de.fhb.service;

import de.fhb.entities.Playlist;
import de.fhb.logging.interceptor.EJBLoggerInterceptor;
import de.fhb.repository.PlaylistRepository;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({EJBLoggerInterceptor.class})
public class PlaylistService extends BaseService<Playlist, PlaylistRepository> {

  @EJB
  private PlaylistRepository repository;

  @Override
  protected PlaylistRepository getRepository() {
    return repository;
  }

  public Playlist getPlaylistByCode(String code) {
    return repository.getPlaylistByCode(code);
  }

  public List<Playlist> getAllPlaylistsByCategory(long id) {
    // TODO Implement getAllPlaylistsByCategory(long)
    // Never return null. instead return an empty List
    return new ArrayList<Playlist>();
  }
}