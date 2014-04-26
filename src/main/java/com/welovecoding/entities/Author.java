package com.welovecoding.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({})
public class Author extends BaseEntity {

  @Size(min = 0, max = 1024)
  @Column(length = 1024)
  private String description;
  @Size(min = 0, max = 255)
  private String website;
  @Size(min = 0, max = 255)
  private String channelUrl;

  public Author() {
    this.description = "";
    this.website = "";
    this.channelUrl = "";
  }

  public Author(String name) {
    this();
    this.setName(name);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getChannelUrl() {
    return channelUrl;
  }

  public void setChannelUrl(String channelUrl) {
    this.channelUrl = channelUrl;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
