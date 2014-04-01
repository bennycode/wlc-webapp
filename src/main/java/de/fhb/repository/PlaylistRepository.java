package de.fhb.repository;

import static de.fhb.config.Packages.PERSISTENCE_UNIT_NAME;
import de.fhb.entities.Playlist;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
//@Interceptors({EJBLoggerInterceptor.class})
public class PlaylistRepository extends AbstractRepository<Playlist> {

  public PlaylistRepository() {
    super(Playlist.class);
  }

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public Playlist getPlaylistByCode(String code) {
    Playlist playlist = null;
    try {
      playlist = em.createNamedQuery(Playlist.FIND_BY_CODE, Playlist.class).setParameter("code", code).getSingleResult();
    } catch (NoResultException e) {
    }

    return playlist;
  }
}
