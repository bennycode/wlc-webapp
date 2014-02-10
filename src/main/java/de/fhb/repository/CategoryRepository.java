package com.welovecoding.web.jpa.session;

import de.fhb.entities.Category;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * @author Benny Neugebauer (bn@bennyn.de)
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {
  @PersistenceContext(unitName = "com.welovecoding.web_wlc-webapp_war_1.0-SNAPSHOTPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CategoryFacade() {
    super(Category.class);
  }

}
