package de.fhb.service;

import de.fhb.entities.Playlist;
import de.fhb.logging.interceptor.EJBLoggerInterceptor;
import de.fhb.repository.PlaylistRepository;
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

}
