package com.welovecoding.tutorial.data.comment.entity;

import com.welovecoding.tutorial.data.base.BaseEntity;
import javax.persistence.Entity;

/**
 *
 * @author Michael Koppen
 */
@Entity
public class Comment extends BaseEntity {

  private String text;

  public Comment() {
  }

  public Comment(String title, String text) {
    this.text = text;
    this.setName(title);
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTitle() {
    return getName();
  }

  public void setTitle(String title) {
    setName(title);
  }

}
