package com.welovecoding.tutorial.data.comment;

import com.welovecoding.tutorial.data.base.BaseService;
import com.welovecoding.tutorial.data.comment.entity.Attachment;
import com.welovecoding.tutorial.data.comment.entity.Comment;
import com.welovecoding.tutorial.data.comment.entity.CommentBag;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CommentService extends BaseService<Comment, CommentRepository> {

  @EJB
  private CommentRepository repository;
  @EJB
  private CommentBagService commentBagService;
  @EJB
  private OwnCacheServerService cacheService;

  public CommentService() {
    super(Comment.class);
  }

  @PostConstruct
  public void init() {
  }

  @Override
  protected CommentRepository getRepository() {
    return repository;
  }

  @Override
  protected OwnCacheServerService getCache() {
    return cacheService;
  }

  /**
   * NOT FOR PUBLIC USAGE YET
   *
   * @param commentBag
   * @param comment
   * @return
   * @throws Exception
   */
  private Comment create(CommentBag commentBag, Comment comment) throws Exception {
    if (commentBag == null) {
      throw new Exception("CommentBag is null");

    }
    commentBag = commentBagService.find(commentBag.getAttachment());
    comment = repository.create(comment);
    commentBag.getComments().add(comment);
    commentBagService.edit(commentBag);
    return comment;
  }

  /**
   * This method is not supported in this service.
   *
   * @param entity
   */
  @Override
  public void create(Comment entity) {
    throw new UnsupportedOperationException("This Operation is not supported. Please consider using #createComment(Long, Class, Comment) or #createComment(Attachment, Comment).");
  }

  @Override
  public void batchCreate(List<Comment> entityList) {
    // TODO implement batchCreate with attachment or ID and Class
    throw new UnsupportedOperationException("This Operation is not supported. Please consider using #createComment(Long, Class, Comment) or #createComment(Attachment, Comment).");
  }

  public Comment create(Long id, Class<?> clazz, Comment comment) {
    CommentBag commentBag = commentBagService.find(new Attachment(id, clazz));
    if (commentBag == null) {
      commentBag = commentBagService.create(new Attachment(id, clazz));
    }
    comment = repository.create(comment);
    commentBag.getComments().add(comment);
    commentBagService.edit(commentBag);
    return comment;
  }

  public Comment create(Attachment attachment, Comment comment) {
    CommentBag commentBag = commentBagService.find(attachment);
    if (commentBag == null) {
      commentBag = commentBagService.create(attachment);
    }
    comment = repository.create(comment);
    commentBag.getComments().add(comment);
    commentBagService.edit(commentBag);
    return comment;
  }

  /**
   * NOT FOR PUBLIC USAGE YET
   *
   * @param commentBag
   * @return
   */
  private List<Comment> findAllAttached(CommentBag commentBag) {
    if (commentBag != null) {
      commentBag = commentBagService.find(commentBag.getAttachment());
    }
    if (commentBag == null) {
      return null;
    }
    return commentBag.getComments();
  }

  public List<Comment> findAllAttached(Attachment attachment) {
    CommentBag commentBag = commentBagService.find(attachment);
    if (commentBag == null) {
      return null;
    }
    return commentBag.getComments();
  }

  public List<Comment> findAllAttached(Long id, Class<?> clazz) {
    CommentBag commentBag = commentBagService.find(id, clazz);
    if (commentBag == null) {
      return null;
    }
    return commentBag.getComments();
  }

  @Override
  protected Set<String> typesToClear() {
    return new HashSet<>(Arrays.asList(new String[]{}));
  }
}
