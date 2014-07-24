package com.welovecoding.tutorial.data.comment;

import com.welovecoding.tutorial.data.comment.entity.Attachment;
import com.welovecoding.tutorial.data.comment.entity.CommentBag;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CommentBagService {

  @EJB
  private CommentBagRepository repository;

  public CommentBagService() {
  }

  @PostConstruct
  public void init() {
  }

  protected CommentBagRepository getRepository() {
    return repository;
  }

  public CommentBag getCommentBag(Attachment attachment) {
    return repository.find(attachment);
  }

  public CommentBag getCommentBag(Long id, Class<?> clazz) {
    return getCommentBag(new Attachment(id, clazz));
  }

  public CommentBag createCommentBag(CommentBag commentBag) {
    return repository.create(commentBag);
  }

  public CommentBag createCommentBag(Attachment attachment) {
    return repository.create(new CommentBag(attachment));
  }

  public void deleteCommentBag(CommentBag commentBag) {
    repository.remove(commentBag);
  }

  public CommentBag editCommentBag(CommentBag commentBag) {
    return repository.edit(commentBag);
  }

  /**
   * NOT FOR PUBLIC USE YET
   *
   * @param range
   * @return
   */
  private List<CommentBag> getCommentBagRange(int[] range) {
    return repository.findRange(range);
  }

  public List<CommentBag> getAllCommentBags() {
    return repository.findAll();
  }

}
