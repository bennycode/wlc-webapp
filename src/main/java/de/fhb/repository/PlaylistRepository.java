package de.fhb.repository;

import com.welovecoding.web.config.Names;
import de.fhb.entities.Playlist;
import de.fhb.logging.interceptor.EJBLoggerInterceptor;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MacYser
 */
@Stateless
@Interceptors({EJBLoggerInterceptor.class})
public class PlaylistRepository extends AbstractRepository<Playlist> {

  public PlaylistRepository() {
    super(Playlist.class);
  }

  @PersistenceContext(unitName = Names.PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public Playlist getPlaylistByCode(String code) {
    Playlist playlist = null;
    try {
      playlist = em.createNamedQuery("Playlist.findByCode", Playlist.class).setParameter("code", code).getSingleResult();
    } catch (NoResultException e) {
    }

    return playlist;
  }
}
