package com.welovecoding.tutorial.data.category;

import com.welovecoding.tutorial.data.base.BaseEntity;
import static com.welovecoding.tutorial.data.category.Category.FIND_BY_ID;
import static com.welovecoding.tutorial.data.category.Category.FIND_BY_SLUG;
import static com.welovecoding.tutorial.data.category.Category.ORDER_BY_NAME;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
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
  @NamedQuery(name = FIND_BY_ID, query = "SELECT c FROM Category c WHERE c.id = :id"),
  @NamedQuery(name = FIND_BY_SLUG, query = "SELECT c FROM Category c WHERE c.slug = :categoryslug"),
  @NamedQuery(name = ORDER_BY_NAME, query = "SELECT c FROM Category c ORDER BY c.name")
})
public class Category extends BaseEntity {

  public static final String FIND_BY_ID = "Category.findById";
  public static final String FIND_BY_SLUG = "Category.findBySlug";
  public static final String ORDER_BY_NAME = "Category.orderByName";

  private static final long serialVersionUID = 1L;

  @NotNull
  @Size(min = 1, max = 7)
  @Basic(optional = false)
  private String color;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
  private List<Playlist> playlists;

  public Category() {
    this.playlists = new ArrayList<>();
    this.color = "#000000";
  }

  public Category(String color) {
    this();
    this.color = color;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public List<Playlist> getPlaylists() {
    return playlists;
  }

  public void setPlaylists(List<Playlist> playlists) {
    this.playlists = playlists;
  }
}
