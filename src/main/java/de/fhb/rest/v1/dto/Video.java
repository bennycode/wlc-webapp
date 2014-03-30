package de.fhb.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
  "id",
  "name",
  "description",
  "code",
  "previewImageUrl",
  "downloadUrl",
  "permalink"
})
public class Video {

  private long id;
  private String name;
  private String description;
  private String code;
  private String previewImageUrl;
  private String downloadUrl;
  private String permalink;
  @JsonIgnore
  private Playlist playlist;

  public Video() {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if (description == null || description.isEmpty()) {
    } else {
      this.description = description;
    }
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    if (code == null || code.isEmpty()) {
    } else {
      this.code = code;
    }
  }

  public String getPreviewImageUrl() {
    return previewImageUrl;
  }

  public void setPreviewImageUrl(String previewImageUrl) {
    if (previewImageUrl == null || previewImageUrl.isEmpty()) {
    } else {
      this.previewImageUrl = previewImageUrl;
    }
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public void setDownloadUrl(String downloadUrl) {
    if (downloadUrl == null || downloadUrl.isEmpty()) {
    } else {
      this.downloadUrl = downloadUrl;
    }
  }

  public String getPermalink() {
    return this.permalink;
  }

  public void setPermalink(String permalink) {
    if (permalink == null || permalink.isEmpty()) {
    } else {
      this.permalink = permalink;
    }
  }

  public Playlist getPlaylist() {
    return playlist;
  }

  public void setPlaylist(Playlist playlist) {
    this.playlist = playlist;
  }

}
