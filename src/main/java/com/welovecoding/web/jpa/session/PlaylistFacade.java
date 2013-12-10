package com.welovecoding.web.jpa.session;

import com.welovecoding.web.entities.Playlist;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * @author Benny Neugebauer (bn@bennyn.de)
 */
@Stateless
public class PlaylistFacade extends AbstractFacade<Playlist> {
  @PersistenceContext(unitName = "com.welovecoding.web_wlc-webapp_war_1.0-SNAPSHOTPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public PlaylistFacade() {
    super(Playlist.class);
  }

}
