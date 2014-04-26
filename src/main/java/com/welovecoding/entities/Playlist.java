package com.welovecoding.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
  @NamedQuery(name = "Playlist.findByCode", query = "SELECT p FROM Playlist p WHERE p.code = :code"),
  @NamedQuery(name = "Playlist.findAllInCategory", query = "SELECT p FROM Playlist p WHERE p.category.id = :categoryid"),
  @NamedQuery(name = "Playlist.likeName", query = "SELECT p FROM Playlist p WHERE UPPER(p.name) LIKE UPPER(:keyword)"),
  @NamedQuery(name = "Playlist.findInCategory", query = "SELECT p FROM Playlist p WHERE p.id = :playlistid AND p.category.id = :categoryid")
})
public class Playlist extends BaseEntity {

  public static final String FIND_BY_CODE = "Playlist.findByCode";
  public static final String LIKE_NAME = "Playlist.likeName";
  public static final String FIND_IN_CATEGORY = "Playlist.findInCategory";
  public static final String FIND_ALL_IN_CATEGORY = "Playlist.findAllInCategory";

  @Embedded @Enumerated(EnumType.STRING)
  private Provider provider;

  @Embedded @Enumerated(EnumType.STRING)
  private Difficulty difficulty;

  @NotNull
  @ManyToOne(cascade = CascadeType.PERSIST)
  private Category category;

  @NotNull
  @ManyToOne(cascade = CascadeType.PERSIST)
  private Author author;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "playlist")
  private List<Video> videos;

  private String code;

  @Embedded @Enumerated(EnumType.STRING)
  private LanguageCode languageCode;

  @Size(min = 0, max = 255)
  private String description;

  private boolean enabled;

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String slug;

  public Playlist() {
    this.enabled = true;
    this.videos = new ArrayList<>();
    this.provider = Provider.YOUTUBE;
  }

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public List<Video> getVideos() {
    return videos;
  }

  public void setVideos(List<Video> videoList) {
    this.videos = videoList;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public LanguageCode getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(LanguageCode languageCode) {
    this.languageCode = languageCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

}
