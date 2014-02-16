package de.fhb.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
  @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
  @NamedQuery(name = "Author.getCount", query = "SELECT COUNT(a) FROM Author a")
})
public class Author extends BaseEntity {

  public Author() {
  }

  public Author(String name) {
    this.setName(name);
  }

  @Override
  public String toString() {
    return super.toString(); //To change body of generated methods, choose Tools | Templates.
  }
}
