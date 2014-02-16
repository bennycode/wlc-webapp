package de.fhb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Playlist extends BaseEntity implements Serializable {

  @NotNull
  @ManyToOne
  private Category category;

  @NotNull
  @ManyToOne
  private Author author;

  @OneToMany
  private List<Video> videoList;
  private String code;

  public Playlist() {
    videoList = new ArrayList<>();
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

  public List<Video> getVideoList() {
    return videoList;
  }

  public void setVideoList(List<Video> videoList) {
    this.videoList = videoList;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

}
