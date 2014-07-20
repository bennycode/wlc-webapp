package com.welovecoding.tutorial.data.playlist;

import com.welovecoding.tutorial.data.base.BaseRepository;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class PlaylistRepository extends BaseRepository<Playlist> {

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
      // NOP
    }

    return playlist;
  }

  public Playlist findInCategory(Long categoryid, Long playlistid) {
    Playlist playlist = null;

    try {
      playlist = em.createNamedQuery(Playlist.FIND_IN_CATEGORY, Playlist.class).
              setParameter("playlistid", playlistid).
              setParameter("categoryid", categoryid).
              getSingleResult();
    } catch (NoResultException e) {
      // NOP
    }

    return playlist;
  }

  public Playlist findByCategoryAndSlug(long categoryid, String slug) {
    Playlist result = null;
    try {
      result = em.createNamedQuery(Playlist.FIND_BY_CATEGORY_AND_SLUG, Playlist.class).
              setParameter("categoryid", categoryid).
              setParameter("playlistslug", slug).
              getSingleResult();
    } catch (NoResultException ex) {
      // NOP
    }
    return result;
  }
}
