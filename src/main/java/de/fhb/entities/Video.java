package de.fhb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Video extends BaseEntity {

  @Column(unique = true)
  private String code;
  private String description;

  public Video() {
  }

  public Video(String code, String name, String description) {
    this.code = code;
    super.setName(name);
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
