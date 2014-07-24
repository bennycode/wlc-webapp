package com.welovecoding.tutorial.data.comment;

import com.welovecoding.tutorial.data.base.BaseRepository;
import com.welovecoding.tutorial.data.comment.entity.CommentBag;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CommentBagRepository extends BaseRepository<CommentBag> {

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  EntityManager em;

  public CommentBagRepository() {
    super(CommentBag.class);
  }

  @Override
  public EntityManager getEntityManager() {
    return em;
  }

}
