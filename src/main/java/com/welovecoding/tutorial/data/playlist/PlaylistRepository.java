package com.welovecoding.tutorial.data.playlist;

import com.welovecoding.tutorial.data.base.BaseRepository;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Stateless
public class PlaylistRepository extends BaseRepository<Playlist> {

  private static final Logger LOG = Logger.getLogger(PlaylistRepository.class.getName());

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

  public Playlist findByCategoryAndSlug(Long categoryid, String slug) {
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

  public List<Playlist> findAllInCategory(Long categoryid) {
    List<Playlist> result = new ArrayList<Playlist>();
    try {
      result = em.createNamedQuery(Playlist.FIND_ALL_IN_CATEGORY, Playlist.class).
              setParameter("categoryid", categoryid).
              getResultList();
    } catch (IllegalArgumentException | PersistenceException e) {
      LOG.log(Level.SEVERE, "Could not find all entities!", e);
    }
    return result;
  }

}
