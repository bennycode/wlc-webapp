package de.fhb.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
  "name",
  "website",
  "description"
})
public class AuthorDTO {

  private String name;
  private String website;
  private String description;

  public AuthorDTO() {
  }

  public AuthorDTO(String name, String website, String description) {
    this.name = name;
    this.website = website;
    this.description = description;
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

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    if (website == null || website.isEmpty()) {
    } else {
      this.website = website;
    }
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

}
