package com.welovecoding.tutorial.api.v2.dto;

import java.util.List;

public class PlaylistDTO extends BaseDTO {

  private String code;
  private String slug;
  private String description;
  private List<String> videos;
  private String language;
  private String provider;
  private AuthorDTO author;
  private String category;
  private int numberOfVideos;

  public PlaylistDTO() {
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getVideos() {
    return videos;
  }

  public void setVideos(List<String> videoList) {
    this.videos = videoList;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguageCode(String language) {
    this.language = language;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public AuthorDTO getAuthor() {
    return author;
  }

  public void setAuthor(AuthorDTO author) {
    this.author = author;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getNumberOfVideos() {
    return numberOfVideos;
  }

  public void setNumberOfVideos(int numberOfVideos) {
    this.numberOfVideos = numberOfVideos;
  }

}
