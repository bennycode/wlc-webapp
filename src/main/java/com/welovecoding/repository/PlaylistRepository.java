package com.welovecoding.repository;

import static com.welovecoding.config.Packages.PERSISTENCE_UNIT_NAME;
import com.welovecoding.entities.Playlist;
import java.util.List;
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

  public Playlist findByCode(String code) {
    Playlist playlist = null;
    try {
      playlist = em.createNamedQuery(Playlist.FIND_BY_CODE, Playlist.class).
              setParameter("code", code).
              getSingleResult();
    } catch (NoResultException e) {
    }

    return playlist;
  }

  public List<Playlist> findAllInCategory(Long categoryid) {
    List<Playlist> playlists = null;
    try {
      playlists = em.createNamedQuery(Playlist.FIND_ALL_IN_CATEGORY, Playlist.class).
              setParameter("categoryid", categoryid).
              getResultList();
    } catch (NoResultException e) {
    }
    return playlists;
  }

  public Playlist findInCategory(Long categoryid, Long playlistid) {
    Playlist playlist = null;

    try {
      playlist = em.createNamedQuery(Playlist.FIND_IN_CATEGORY, Playlist.class).
              setParameter("playlistid", playlistid).
              setParameter("categoryid", categoryid).
              getSingleResult();
    } catch (NoResultException e) {
    }
    return playlist;
  }
}
