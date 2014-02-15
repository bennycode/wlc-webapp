package de.fhb.entities;

import com.github.slugify.Slugify;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Benny Neugebauer (bn@bennyn.de)
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
  @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id"),
  @NamedQuery(name = "Category.findBySlug", query = "SELECT c FROM Category c WHERE c.slug = :slug")
})
public class Category extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @Size(min = 1, max = 7)
  @Basic(optional = false)
  private String color;

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String slug;

  @OneToMany
  private List<Playlist> playlists;

  public Category() {
    this.color = "#000000";
  }

  public Category(String color, String slug) {
    this.color = color;
    this.slug = slug;
  }

  @Override
  public void setName(String name) {
    super.setName(name);
    this.slug = new Slugify().slugify(name);
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public List<Playlist> getPlaylists() {
    return playlists;
  }

  public void setPlaylistList(List<Playlist> playlists) {
    this.playlists = playlists;
  }

  @Override
  public String toString() {
    return super.getName() + " (ID: " + super.getId() + ")";
  }

}
