package de.fhb.rest.v1.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlType(propOrder = {"id", "title", "color"})
public class CategoryDTO extends BaseDTO {

  private Integer id;
  private String title;
  private String slug;
  private String color;
  private List<PlaylistDTO> playlistList;
  private int numberOfVideos;
  private List<String> availableLanguages;
  private String url;

  public CategoryDTO() {
    this.availableLanguages = new ArrayList<String>();
    this.numberOfVideos = 0;
  }

  public CategoryDTO(Integer id) {
    this.availableLanguages = new ArrayList<String>();
    this.numberOfVideos = 0;
    this.id = id;
  }

  public CategoryDTO(Integer id, String title, String slug, String color) {
    this.availableLanguages = new ArrayList<String>();
    this.numberOfVideos = 0;
    this.id = id;
    this.title = title;
    this.slug = slug;
    this.color = color;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @XmlElement(name = "name")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @XmlTransient
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

  @XmlTransient
  public List<PlaylistDTO> getPlaylistList() {
    return playlistList;
  }

  public void setPlaylistList(List<PlaylistDTO> playlistList) {
    this.playlistList = playlistList;
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

  @XmlTransient
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 11 * hash + Objects.hashCode(this.id);
    hash = 11 * hash + Objects.hashCode(this.title);
    hash = 11 * hash + Objects.hashCode(this.slug);
    hash = 11 * hash + Objects.hashCode(this.color);
    hash = 11 * hash + Objects.hashCode(this.playlistList);
    hash = 11 * hash + this.numberOfVideos;
    hash = 11 * hash + Objects.hashCode(this.availableLanguages);
    hash = 11 * hash + Objects.hashCode(this.url);
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
    final CategoryDTO other = (CategoryDTO) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "CategoryDTO{" + "id=" + id + '}';
  }
}
