package com.welovecoding.tutorial.data.comment.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

/**
 *
 * @author Michael Koppen
 */
@Entity
@Cacheable
@NamedQueries({
  @NamedQuery(name = "CommentBag.findAllByIdAndType", query = "SELECT cb FROM CommentBag cb WHERE cb.attachment = :attachment")
})
public class CommentBag implements Serializable {

  @EmbeddedId
  private Attachment attachment;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "COMMENTBAG_COMMENT",
          joinColumns = {
            @JoinColumn(name = "ATTACHED_TO_ID", referencedColumnName = "ATTACHED_TO_ID"),
            @JoinColumn(name = "ATTACHED_TO_TYPE", referencedColumnName = "ATTACHED_TO_TYPE")},
          inverseJoinColumns = {
            @JoinColumn(name = "COMMENT_ID", referencedColumnName = "ID")})
  @JoinFetch(JoinFetchType.INNER)
  private List<Comment> comments;

  public CommentBag() {
    comments = new ArrayList<>();
  }

  public CommentBag(Attachment attachment) {
    this.attachment = attachment;
    comments = new ArrayList<>();
  }

  public CommentBag(Attachment attachment, List<Comment> comments) {
    this.attachment = attachment;
    this.comments = comments;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }
}
