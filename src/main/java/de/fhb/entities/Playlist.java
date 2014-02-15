package de.fhb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Playlist extends BaseEntity implements Serializable {

  @ManyToOne
  private Category category;

  public Playlist() {
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

}
