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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "playlists")
@NamedQueries({
  @NamedQuery(name = "Playlist.findAll", query = "SELECT p FROM Playlist p"),
  @NamedQuery(name = "Playlist.findById", query = "SELECT p FROM Playlist p WHERE p.id = :id"),
  @NamedQuery(name = "Playlist.findByCode", query = "SELECT p FROM Playlist p WHERE p.code = :code"),
  @NamedQuery(name = "Playlist.findByCreated", query = "SELECT p FROM Playlist p WHERE p.created = :created"),
  @NamedQuery(name = "Playlist.findByLastmodified", query = "SELECT p FROM Playlist p WHERE p.lastmodified = :lastmodified"),
  @NamedQuery(name = "Playlist.findBySlug", query = "SELECT p FROM Playlist p WHERE p.slug = :slug"),
  @NamedQuery(name = "Playlist.findByTitle", query = "SELECT p FROM Playlist p WHERE p.title = :title")})
public class Playlist implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Size(max = 255)
  @Column(name = "CODE")
  private String code;
  @Column(name = "CREATED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;
  @Lob
  @Size(max = 2147483647)
  @Column(name = "DESCRIPTION")
  private String description;
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
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "playlistId", fetch = FetchType.EAGER)
  private List<Videos> videosList;
  @JoinColumn(name = "language_id", referencedColumnName = "ID")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Language languageId;
  @JoinColumn(name = "provider", referencedColumnName = "ID")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Provider provider;
  @JoinColumn(name = "owner_id", referencedColumnName = "ID")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Owner ownerId;
  @JoinColumn(name = "category_id", referencedColumnName = "ID")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Category categoryId;

  public Playlist() {
  }

  public Playlist(Integer id) {
    this.id = id;
  }

  public Playlist(Integer id, String slug, String title) {
    this.id = id;
    this.slug = slug;
    this.title = title;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public List<Videos> getVideosList() {
    return videosList;
  }

  public void setVideosList(List<Videos> videosList) {
    this.videosList = videosList;
  }

  public Language getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Language languageId) {
    this.languageId = languageId;
  }

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }

  public Owner getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Owner ownerId) {
    this.ownerId = ownerId;
  }

  public Category getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Category categoryId) {
    this.categoryId = categoryId;
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
    if (!(object instanceof Playlist)) {
      return false;
    }
    Playlist other = (Playlist) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.welovecoding.web.entities.Playlist[ id=" + id + " ]";
  }

}
