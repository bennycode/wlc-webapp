package de.fhb.repository;

import static de.fhb.config.Packages.PERSISTENCE_UNIT_NAME;
import de.fhb.entities.Video;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
//@Interceptors({EJBLoggerInterceptor.class})
public class VideoRepository extends AbstractRepository<Video> {

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

  public Video getVideoByCode(String code) {
    Video video = null;
    try {
      video = em.createNamedQuery(Video.FIND_BY_CODE, Video.class).setParameter("code", code).getSingleResult();
    } catch (NoResultException e) {
      LOG.log(Level.WARNING, "Video with code {0} cannot be found.", code);
    }

    return video;
  }

  public Video findInCategoryAndPlaylist(Long categoryid, Long playlistid, Long videoid) {
    Video video = null;
    try {
      video = em.createNamedQuery(Video.FIND_IN_CATEGORY_AND_PLAYLIST, Video.class).
              setParameter("videoid", videoid).
              setParameter("playlistid", playlistid).
              setParameter("categoryid", categoryid).
              getSingleResult();
    } catch (NoResultException e) {
    }
    return video;
  }

  public Video findInPlaylist(Long playlistid, Long videoid) {
    Video video = null;
    try {
      video = em.createNamedQuery(Video.FIND_IN_PLAYLIST, Video.class).
              setParameter("videoid", videoid).
              setParameter("playlistid", playlistid).
              getSingleResult();
    } catch (NoResultException e) {
    }
    return video;
  }

  public Video findInCategory(Long categoryid, Long videoid) {
    Video video = null;
    try {
      video = em.createNamedQuery(Video.FIND_IN_CATEGORY, Video.class).
              setParameter("videoid", videoid).
              setParameter("categoryid", categoryid).
              getSingleResult();
    } catch (NoResultException e) {
    }
    return video;
  }

  public List<Video> findAllInCategory(Long categoryid) {
    List<Video> videos = null;
    try {
      videos = em.createNamedQuery(Video.FIND_ALL_IN_CATEGORY, Video.class).
              setParameter("categoryid", categoryid).
              getResultList();
    } catch (NoResultException e) {
    }
    return videos;
  }

  public List<Video> findAllInPlaylist(Long playlistid) {
    List<Video> videos = null;
    try {
      videos = em.createNamedQuery(Video.FIND_ALL_IN_PLAYLIST, Video.class).
              setParameter("playlistid", playlistid).
              getResultList();
    } catch (NoResultException e) {
    }
    return videos;
  }
}
