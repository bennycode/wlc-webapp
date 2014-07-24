package com.welovecoding.tutorial.data.comment;

import com.welovecoding.tutorial.data.base.BaseRepository;
import com.welovecoding.tutorial.data.comment.entity.Comment;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CommentRepository extends BaseRepository<Comment> {

  @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
  EntityManager em;

  public CommentRepository() {
    super(Comment.class);
  }

  @PostConstruct
  private void init() {
  }

  @Override
  public EntityManager getEntityManager() {
    return em;
  }

}
