package com.welovecoding.tutorial.data.comment.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Michael Koppen
 */
@Embeddable
@Table(uniqueConstraints
        = @UniqueConstraint(
                columnNames = {
                  "ATTACHED_TO_ID",
                  "ATTACHED_TO_TYPE"
                }))
public class Attachment implements Serializable {

  @Column(name = "ATTACHED_TO_ID", nullable = false)
  private Long attachedToId;
  @Column(name = "ATTACHED_TO_TYPE", nullable = false)
  private String attachedToType;

  public Attachment() {
  }

  public Attachment(Long attachedToId, Class<?> attachedToType) {
    this.attachedToId = attachedToId;
    this.attachedToType = attachedToType.getName();
  }

  public Long getAttachedToId() {
    return attachedToId;
  }

  public void setAttachedToId(Long attachedToId) {
    this.attachedToId = attachedToId;
  }

  public String getAttachedToType() {
    return attachedToType;
  }

  public void setAttachedToType(Class<?> attachedToType) {
    this.attachedToType = attachedToType.getName();
  }
}
