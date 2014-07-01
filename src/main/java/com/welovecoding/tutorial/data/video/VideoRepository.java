package com.welovecoding.tutorial.data.video;

import com.welovecoding.tutorial.data.base.BaseRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

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
    return em.createNamedQuery(Video.FIND_IN_CATEGORY_AND_PLAYLIST, Video.class).
            setParameter("videoid", videoid).
            setParameter("playlistid", playlistid).
            setParameter("categoryid", categoryid).
            getSingleResult();
  }

  public Video findInPlaylist(Long playlistid, Long videoid) {
    return em.createNamedQuery(Video.FIND_IN_PLAYLIST, Video.class).
            setParameter("videoid", videoid).
            setParameter("playlistid", playlistid).
            getSingleResult();
  }

  public Video findInCategory(Long categoryid, Long videoid) {
    return em.createNamedQuery(Video.FIND_IN_CATEGORY, Video.class).
            setParameter("videoid", videoid).
            setParameter("categoryid", categoryid).
            getSingleResult();
  }

  public List<Video> findAllInCategory(Long categoryid) {
    return em.createNamedQuery(Video.FIND_ALL_IN_CATEGORY, Video.class).
            setParameter("categoryid", categoryid).
            getResultList();
  }

  public List<Video> getVideosByPlaylistId(Long playlistId) {
    return em.createNamedQuery(Video.FIND_ALL_IN_PLAYLIST, Video.class).
            setParameter("playlistid", playlistId).getResultList();
  }

  public Video getByPlaylistAndSlug(long playlistid, String slug) {
    return em.createNamedQuery(Video.FIND_BY_PLAYLIST_AND_SLUG, Video.class).
            setParameter("playlistid", playlistid).
            setParameter("categoryslug", slug).
            getSingleResult();
  }
}
