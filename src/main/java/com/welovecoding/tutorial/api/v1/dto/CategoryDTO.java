package com.welovecoding.tutorial.api.v1.dto;

import java.util.List;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({
  "id",
  "name",
  "color",
  "numberOfVideos",
  "availableLanguages"
})
public class CategoryDTO {

  private long id;
  private String name;
  private String color;
  private int numberOfVideos;
  private List<String> availableLanguages;

  public CategoryDTO() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
    } else {
      this.name = name;
    }
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    if (color == null || color.isEmpty()) {
    } else {
      this.color = color;
    }
  }

  public int getNumberOfVideos() {
    return numberOfVideos;
  }

  public void setNumberOfVideos(int numberOfVideos) {
    this.numberOfVideos = numberOfVideos;
  }

  public List<String> getAvailableLanguages() {
    return availableLanguages;
  }

  public void setAvailableLanguages(List<String> availableLanguages) {
    this.availableLanguages = availableLanguages;
  }

}
