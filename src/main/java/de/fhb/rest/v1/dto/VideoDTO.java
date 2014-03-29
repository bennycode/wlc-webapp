package de.fhb.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "title", "description", "code", "previewImageUrl", "downloadUrl", "permalink"})
public class VideoDTO extends BaseDTO {

  private Integer id;
  private String code;
  @JsonProperty("name")//new 
  private String title;
  private String description;
  private PlaylistDTO playlist;
  // TODO: Persist all transient variables
  @Transient
  private String previewImageUrl;
  @Transient
  private String downloadUrl;
  @Transient
  private String permalink;

  public VideoDTO() {
  }

  public VideoDTO(Integer id) {
    this.id = id;
  }

  public VideoDTO(Integer id, String code, String title) {
    this.id = id;
    this.code = code;
    this.title = title;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @XmlElement(name = "name")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @XmlTransient
  public PlaylistDTO getPlaylist() {
    return playlist;
  }

  public void setPlaylist(PlaylistDTO playlist) {
    this.playlist = playlist;
  }

  public String getPreviewImageUrl() {
    return previewImageUrl;
  }

  public void setPreviewImageUrl(String previewImageUrl) {
    this.previewImageUrl = previewImageUrl;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public void setDownloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
  }

  public String getPermalink() {
    return permalink;
  }

  public void setPermalink(String permalink) {
    this.permalink = permalink;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 47 * hash + Objects.hashCode(this.id);
    hash = 47 * hash + Objects.hashCode(this.code);
    hash = 47 * hash + Objects.hashCode(this.title);
    hash = 47 * hash + Objects.hashCode(this.description);
    hash = 47 * hash + Objects.hashCode(this.playlist);
    hash = 47 * hash + Objects.hashCode(this.previewImageUrl);
    hash = 47 * hash + Objects.hashCode(this.downloadUrl);
    hash = 47 * hash + Objects.hashCode(this.permalink);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final VideoDTO other = (VideoDTO) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "VideoDTO{" + "id=" + id + '}';
  }
}
