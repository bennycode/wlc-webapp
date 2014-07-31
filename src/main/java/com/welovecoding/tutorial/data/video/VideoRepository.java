package com.welovecoding.tutorial.data.video;

import com.welovecoding.tutorial.data.base.BaseRepository;
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
public class VideoRepository extends BaseRepository<Video> {

  private static final Logger LOG = Logger.getLogger(VideoRepository.class.getName());

  public VideoRepository() {
    super(Video.class);
  }

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public Video findByCode(String code) {
    Video video = null;

    try {
      video = em.createNamedQuery(Video.FIND_BY_CODE, Video.class).setParameter("code", code).getSingleResult();
    } catch (NoResultException e) {
      LOG.log(Level.WARNING, "Video with code {0} cannot be found.", code);
    }

    return video;
  }

  public Video findInCategoryAndPlaylist(Long categoryid, Long playlistid, Long videoid) {
    Video result = null;
    try {
      result = em.createNamedQuery(Video.FIND_IN_CATEGORY_AND_PLAYLIST, Video.class).
              setParameter("videoid", videoid).
              setParameter("playlistid", playlistid).
              setParameter("categoryid", categoryid).
              getSingleResult();
    } catch (NoResultException ex) {
      // NOP
    }
    return result;
  }

  public Video findInPlaylist(Long playlistid, Long videoid) {
    Video result = null;
    try {
      result = em.createNamedQuery(Video.FIND_IN_PLAYLIST, Video.class).
              setParameter("videoid", videoid).
              setParameter("playlistid", playlistid).
              getSingleResult();
    } catch (NoResultException ex) {
      // NOP
    }
    return result;
  }

  public List<Video> findAllInPlaylist(Long playlistid) {
    List<Video> result = new ArrayList<Video>();
    try {
      result = em.createNamedQuery(Video.FIND_ALL_IN_PLAYLIST, Video.class).
              setParameter("playlistid", playlistid).
              getResultList();
    } catch (IllegalArgumentException | PersistenceException e) {
      LOG.log(Level.SEVERE, "Could not find all entities!", e);
    }
    return result;
  }

  public List<Video> findAllInCategoryAndPlaylist(Long categoryid, Long playlistid) {
    List<Video> result = new ArrayList<Video>();
    try {
      result = em.createNamedQuery(Video.FIND_ALL_IN_CATEGORY_AND_PLAYLIST, Video.class).
              setParameter("categoryid", categoryid).
              setParameter("playlistid", playlistid).
              getResultList();
    } catch (IllegalArgumentException | PersistenceException e) {
      LOG.log(Level.SEVERE, "Could not find all entities!", e);
    }
    return result;
  }

  public Video findInCategory(Long categoryid, Long videoid) {
    Video result = null;
    try {
      result = em.createNamedQuery(Video.FIND_IN_CATEGORY, Video.class).
              setParameter("videoid", videoid).
              setParameter("categoryid", categoryid).
              getSingleResult();
    } catch (NoResultException ex) {
      // NOP
    }
    return result;
  }

  public List<Video> findAllInCategory(Long categoryid) {
    return em.createNamedQuery(Video.FIND_ALL_IN_CATEGORY, Video.class).
            setParameter("categoryid", categoryid).
            getResultList();
  }

  public Video findByPlaylistAndSlug(long playlistid, String slug) {
    Video result = null;
    try {
      result = em.createNamedQuery(Video.FIND_BY_PLAYLIST_AND_SLUG, Video.class).
              setParameter("playlistid", playlistid).
              setParameter("categoryslug", slug).
              getSingleResult();
    } catch (NoResultException ex) {
      // NOP
    }
    return result;
  }
}
