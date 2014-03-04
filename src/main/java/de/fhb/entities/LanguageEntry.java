package de.fhb.entities;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Benny
 */
// TODO This is an old entity which maybe have to be mapped to the new structure
@Entity
@Cacheable
@NamedQueries({
  @NamedQuery(name = "LanguageEntry.findByName", query = "SELECT l FROM LanguageEntry l WHERE l.title = :title")})
public class LanguageEntry extends BaseEntity {

  @Column(nullable = false, length = 255, unique = true)
  private String title;

  public LanguageEntry() {
  }

  public LanguageEntry(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
