package de.fhb.entities;

import javax.persistence.Entity;

@Entity
public class Video extends BaseEntity {

  private String code;
  private String title;
  private String description;

  public Video() {
  }

  public Video(String code, String title, String description) {
    this.code = code;
    this.title = title;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }
}
