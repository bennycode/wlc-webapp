package de.fhb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Playlist extends BaseEntity implements Serializable {

  @NotNull
  @ManyToOne
  private Category category;

  @NotNull
  @ManyToOne
  private Author author;

  private String code;

  public Playlist() {
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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
}
