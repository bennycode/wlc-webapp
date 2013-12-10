package com.welovecoding.web.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * @author Benny Neugebauer (bn@bennyn.de)
 */
@Entity
@Table(name = "categories")
@NamedQueries({
  @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
  @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id"),
  @NamedQuery(name = "Category.findByColor", query = "SELECT c FROM Category c WHERE c.color = :color"),
  @NamedQuery(name = "Category.findByCreated", query = "SELECT c FROM Category c WHERE c.created = :created"),
  @NamedQuery(name = "Category.findByLastmodified", query = "SELECT c FROM Category c WHERE c.lastmodified = :lastmodified"),
  @NamedQuery(name = "Category.findBySlug", query = "SELECT c FROM Category c WHERE c.slug = :slug"),
  @NamedQuery(name = "Category.findByTitle", query = "SELECT c FROM Category c WHERE c.title = :title")})
public class Category implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 7)
  @Column(name = "COLOR")
  private String color;
  @Column(name = "CREATED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;
  @Column(name = "LASTMODIFIED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastmodified;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "SLUG")
  private String slug;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "TITLE")
  private String title;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId", fetch = FetchType.EAGER)
  private List<Playlist> playlistList;

  public Category() {
  }

  public Category(Integer id) {
    this.id = id;
  }

  public Category(Integer id, String color, String slug, String title) {
    this.id = id;
    this.color = color;
    this.slug = slug;
    this.title = title;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getLastmodified() {
    return lastmodified;
  }

  public void setLastmodified(Date lastmodified) {
    this.lastmodified = lastmodified;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Playlist> getPlaylistList() {
    return playlistList;
  }

  public void setPlaylistList(List<Playlist> playlistList) {
    this.playlistList = playlistList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Category)) {
      return false;
    }
    Category other = (Category) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.welovecoding.web.entities.Category[ id=" + id + " ]";
  }

}
