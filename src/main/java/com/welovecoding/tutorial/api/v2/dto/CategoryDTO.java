package com.welovecoding.tutorial.api.v2.dto;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Michael Koppen
 */
public class CategoryDTO extends BaseDTO {

  private String slug;
  private String color;
  private List<String> playlists;
  private int numberOfVideos;
  private Set<String> languageCodes;

  public CategoryDTO() {
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public List<String> getPlaylists() {
    return playlists;
  }

  public void setPlaylists(List<String> playlists) {
    this.playlists = playlists;
  }

  public int getNumberOfVideos() {
    return numberOfVideos;
  }

  public void setNumberOfVideos(int numberOfVideos) {
    this.numberOfVideos = numberOfVideos;
  }

  public Set<String> getLanguageCodes() {
    return languageCodes;
  }

  public void setLanguageCodes(Set<String> languageCodes) {
    this.languageCodes = languageCodes;
  }

}
