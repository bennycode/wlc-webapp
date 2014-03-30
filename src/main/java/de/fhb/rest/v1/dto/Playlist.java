package de.fhb.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
  "id",
  "name",
  "language",
  "categoryName",
  "providerName",
  "numberOfVideos",
  "description",
  "owner",
  "status"
})
public class Playlist {

  private long id;
  private String name;
  private String language;
  private String categoryName;
  private String providerName;
  private int numberOfVideos;
  private String description;
  private Owner owner;
  private Status status;

  public Playlist() {
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

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

}
