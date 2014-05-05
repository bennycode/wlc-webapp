package com.welovecoding.tutorial.api.v2.dto;

public class AuthorDTO extends BaseDTO {

  private String description;
  private String website;
  private String channelUrl;

  public AuthorDTO() {
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
}
