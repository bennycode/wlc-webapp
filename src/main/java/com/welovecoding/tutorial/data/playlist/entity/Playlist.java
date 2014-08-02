package com.welovecoding.tutorial.data.playlist.entity;

import com.welovecoding.tutorial.data.author.Author;
import com.welovecoding.tutorial.data.base.BaseEntity;
import com.welovecoding.tutorial.data.category.Category;
import static com.welovecoding.tutorial.data.playlist.entity.Playlist.FIND_ALL_IN_CATEGORY;
import static com.welovecoding.tutorial.data.playlist.entity.Playlist.FIND_BY_CATEGORY_AND_SLUG;
import static com.welovecoding.tutorial.data.playlist.entity.Playlist.FIND_BY_CODE;
import static com.welovecoding.tutorial.data.playlist.entity.Playlist.FIND_IN_CATEGORY;
import static com.welovecoding.tutorial.data.playlist.entity.Playlist.LIKE_NAME;
import com.welovecoding.tutorial.data.video.Video;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
  @NamedQuery(name = FIND_BY_CODE, query = "SELECT p FROM Playlist p WHERE p.code = :code"),
  @NamedQuery(name = FIND_ALL_IN_CATEGORY, query = "SELECT p FROM Playlist p WHERE p.category.id = :categoryid"),
  @NamedQuery(name = LIKE_NAME, query = "SELECT p FROM Playlist p WHERE UPPER(p.name) LIKE UPPER(:keyword)"),
  @NamedQuery(name = FIND_IN_CATEGORY, query = "SELECT p FROM Playlist p WHERE p.id = :playlistid AND p.category.id = :categoryid"),
  @NamedQuery(name = FIND_BY_CATEGORY_AND_SLUG, query = "SELECT p FROM Playlist p WHERE p.slug = :playlistslug AND p.category.id = :categoryid")
})
public class Playlist extends BaseEntity {

  public static final String FIND_BY_CODE = "Playlist.findByCode";
  public static final String LIKE_NAME = "Playlist.likeName";
  public static final String FIND_IN_CATEGORY = "Playlist.findInCategory";
  public static final String FIND_ALL_IN_CATEGORY = "Playlist.findAllInCategory";
  public static final String FIND_BY_CATEGORY_AND_SLUG = "Playlist.findByCategoryAndSlug";

  @Embedded @Enumerated(EnumType.STRING)
  private Provider provider;

  @Embedded @Enumerated(EnumType.STRING)
  private Difficulty difficulty;

  @NotNull @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Category category;

  @NotNull @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Author author;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "playlist", orphanRemoval = true)
  @OrderColumn(name = "ORDERING")
  @Valid
  private List<Video> videos;

  private String code;

  @Embedded @Enumerated(EnumType.STRING)
  private LanguageCode languageCode;

  @Size(min = 0, max = 1024)
  @Column(length = 1024)
  private String description;

  private boolean enabled;

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

  @Override
  public void setName(String name) {
    super.setName(name);
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
