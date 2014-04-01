package de.fhb.entities;

import de.fhb.util.Slugify;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
  @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
  @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id"),
  @NamedQuery(name = "Category.findBySlug", query = "SELECT c FROM Category c WHERE c.slug = :slug"),
  @NamedQuery(name = "Category.orderByName", query = "SELECT c FROM Category c ORDER BY c.name")
})
public class Category extends BaseEntity implements Serializable {

  public static final String FIND_ALL = "Category.findAll";
  public static final String FIND_BY_ID = "Category.findById";
  public static final String FIND_BY_SLUG = "Category.findBySlug";
  public static final String ORDER_BY_NAME = "Category.orderByName";

  private static final long serialVersionUID = 1L;

  @NotNull
  @Size(min = 1, max = 7)
  @Basic(optional = false)
  private String color;

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String slug;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
  private List<Playlist> playlists;

  public Category() {
    this.playlists = new ArrayList<>();
    this.color = "#000000";
  }

  public Category(String color, String slug) {
    this();
    this.color = color;
    this.slug = slug;
  }

  @Override
  public void setName(String name) {
    super.setName(name);
    this.slug = Slugify.slugify(name);
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

  public void setPlaylists(List<Playlist> playlists) {
    this.playlists = playlists;
  }

  @Override
  public String toString() {
    return this.getName()+"a";
  }
}
