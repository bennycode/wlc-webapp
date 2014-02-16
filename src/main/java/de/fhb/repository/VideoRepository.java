package de.fhb.repository;

import com.welovecoding.web.config.Names;
import de.fhb.entities.Video;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MacYser
 */
@Stateless
//@Interceptors({EJBLoggerInterceptor.class})
public class VideoRepository extends AbstractRepository<Video> {

  private static final Logger LOG = Logger.getLogger(VideoRepository.class.getName());

  public VideoRepository() {
    super(Video.class);
  }

  @PersistenceContext(unitName = Names.PERSISTENCE_UNIT_NAME)
  EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public Video getVideoByCode(String code) {
    Video video = null;
    try {
      video = em.createNamedQuery("Video.findByCode", Video.class).setParameter("code", code).getSingleResult();
    } catch (NoResultException e) {
      LOG.log(Level.WARNING, "Video with code {0} cannot be found.", code);
    }

    return video;
  }
}
