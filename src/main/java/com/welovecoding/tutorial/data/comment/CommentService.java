package com.welovecoding.tutorial.data.comment;

import com.welovecoding.tutorial.data.base.BaseService;
import com.welovecoding.tutorial.data.comment.entity.Attachment;
import com.welovecoding.tutorial.data.comment.entity.Comment;
import com.welovecoding.tutorial.data.comment.entity.CommentBag;
import de.yser.ownsimplecache.OwnCacheServerService;
import java.util.ArrayList;
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

  public Comment getComment(Long id) {
    return repository.find(id);
  }

  /**
   * NOT FOR PUBLIC USE YET
   *
   * @param commentBag
   * @param comment
   * @return
   * @throws Exception
   */
  private Comment createComment(CommentBag commentBag, Comment comment) throws Exception {
    if (commentBag == null) {
      throw new Exception("CommentBag is null");

    }
    commentBag = commentBagService.getCommentBag(commentBag.getAttachment());
    comment = repository.create(comment);
    commentBag.getComments().add(comment);
    commentBagService.editCommentBag(commentBag);
    return comment;
  }

  public Comment createComment(Long id, Class<?> clazz, Comment comment) {
    CommentBag commentBag = commentBagService.getCommentBag(new Attachment(id, clazz));
    if (commentBag == null) {
      commentBag = commentBagService.createCommentBag(new Attachment(id, clazz));
    }
    if (commentBag.getComments() == null) {
      commentBag.setComments(new ArrayList<Comment>());
    }
    comment = repository.create(comment);
    commentBag.getComments().add(comment);
    commentBagService.editCommentBag(commentBag);
    return comment;
  }

  public Comment createComment(Attachment attachment, Comment comment) {
    CommentBag commentBag = commentBagService.getCommentBag(attachment);
    comment = repository.create(comment);
    commentBag.getComments().add(comment);
    commentBagService.editCommentBag(commentBag);
    return comment;
  }

  public void deleteComment(Comment comment) {
    repository.remove(comment);
  }

  public Comment editComment(Comment comment) {
    return repository.edit(comment);
  }

  /**
   * NOT FOR PUBLIC USE YET
   *
   * @param range
   * @return
   */
  private List<Comment> getCommentRange(int[] range) {
    return repository.findRange(range);
  }

  public List<Comment> getAllComments() {
    return repository.findAll();
  }

  /**
   * NOT FOR PUBLIC USE YET
   *
   * @param commentBag
   * @return
   */
  private List<Comment> getAllCommentsOfCommentBag(CommentBag commentBag) {
    if (commentBag != null) {
      commentBag = commentBagService.getCommentBag(commentBag.getAttachment());
    }
    if (commentBag == null) {
      return null;
    }
    return commentBag.getComments();
  }

  public List<Comment> getAllCommentsOfCommentBag(Attachment attachment) {
    CommentBag commentBag = commentBagService.getCommentBag(attachment);
    if (commentBag == null) {
      return null;
    }
    return commentBag.getComments();
  }

  public List<Comment> getAllCommentsOfCommentBag(Long id, Class<?> clazz) {
    CommentBag commentBag = commentBagService.getCommentBag(id, clazz);
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
