package de.fhb.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
  @NamedQuery(name = "Playlist.findByCode", query = "SELECT p FROM Playlist p WHERE p.code = :code"),
  @NamedQuery(name = "Playlist.likeName", query = "SELECT p FROM Playlist p WHERE UPPER(p.name) LIKE UPPER(:keyword)")
})
public class Playlist extends BaseEntity {

  public static final String FIND_BY_CODE = "Playlist.findByCode";
  public static final String LIKE_NAME = "Playlist.likeName";

  @NotNull
  @ManyToOne
  private Category category;

  @NotNull
  @ManyToOne
  private Author author;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "playlist")
  private List<Video> videos;

  @Column(unique = true)
  private String code;

  @Embedded
  private Language languageCode;

  @Embedded
  private Provider providerName;

  @Size(min = 0, max = 255)
  private String description;

  public Playlist() {
    videos = new ArrayList<>();
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    System.out.println(category);
    this.category = category;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    System.out.println(author);
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

  public Language getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(Language languageCode) {
    this.languageCode = languageCode;
  }

  public Provider getProviderName() {
    return providerName;
  }

  public void setProviderName(Provider providerName) {
    this.providerName = providerName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return this.getName();
  }

}
