package com.welovecoding.tutorial.api.v1.dto;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({
  "id",
  "name",
  "language",
  "categoryName",
  "providerName",
  "numberOfVideos",
  "description",
  "owner",
  "status",
  "difficulty"
})
public class PlaylistDTO {

  private long id;
  private String name;
  private String language;
  private String categoryName;
  private String providerName;
  private int numberOfVideos;
  private String description;
  private AuthorDTO owner;
  private StatusDTO status;
//  private String difficulty;

  public PlaylistDTO() {
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

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    if (language == null || language.isEmpty()) {
    } else {
      this.language = language;
    }
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    if (categoryName == null || categoryName.isEmpty()) {
    } else {
      this.categoryName = categoryName;
    }
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    if (providerName == null || providerName.isEmpty()) {
    } else {
      this.providerName = providerName;
    }
  }

  public int getNumberOfVideos() {
    return numberOfVideos;
  }

  public void setNumberOfVideos(int numberOfVideos) {
    this.numberOfVideos = numberOfVideos;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if (description == null || description.isEmpty()) {
    } else {
      this.description = description;
    }
  }

  public AuthorDTO getOwner() {
    return owner;
  }

  public void setOwner(AuthorDTO owner) {
    this.owner = owner;
  }

  public StatusDTO getStatus() {
    return status;
  }

  public void setStatus(StatusDTO status) {
    this.status = status;
  }

//  public String getDifficulty() {
//    return difficulty;
//  }
//
//  public void setDifficulty(String difficulty) {
//    this.difficulty = difficulty;
//  }
}
