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

  protected CommentBag find(Attachment attachment) {
    return repository.find(attachment);
  }

  protected CommentBag find(Long id, Class<?> clazz) {
    return this.find(new Attachment(id, clazz));
  }

  protected CommentBag create(CommentBag commentBag) {
    return repository.create(commentBag);
  }

  protected CommentBag create(Attachment attachment) {
    return repository.create(new CommentBag(attachment));
  }

  protected void remove(CommentBag commentBag) {
    repository.remove(commentBag);
  }

  protected CommentBag edit(CommentBag commentBag) {
    return repository.edit(commentBag);
  }

  /**
   * NOT FOR PUBLIC USAGE YET
   *
   * @param range
   * @return
   */
  private List<CommentBag> findRange(int[] range) {
    return repository.findRange(range);
  }

  protected List<CommentBag> findAll() {
    return repository.findAll();
  }

}
