package de.fhb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
  @NamedQuery(name = "Author.getCount", query = "SELECT COUNT(a) FROM Author a")
})
public class Author extends BaseEntity implements Serializable {

  public Author() {
  }

  public Author(String name) {
    this.setName(name);
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
