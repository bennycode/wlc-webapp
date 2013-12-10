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
@Table(name = "languages")
@NamedQueries({
  @NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l"),
  @NamedQuery(name = "Language.findById", query = "SELECT l FROM Language l WHERE l.id = :id"),
  @NamedQuery(name = "Language.findByCreated", query = "SELECT l FROM Language l WHERE l.created = :created"),
  @NamedQuery(name = "Language.findByLastmodified", query = "SELECT l FROM Language l WHERE l.lastmodified = :lastmodified"),
  @NamedQuery(name = "Language.findByName", query = "SELECT l FROM Language l WHERE l.name = :name")})
public class Language implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "CREATED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;
  @Column(name = "LASTMODIFIED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastmodified;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "NAME")
  private String name;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageId", fetch = FetchType.EAGER)
  private List<Playlist> playlistList;

  public Language() {
  }

  public Language(Integer id) {
    this.id = id;
  }

  public Language(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    if (!(object instanceof Language)) {
      return false;
    }
    Language other = (Language) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.welovecoding.web.entities.Language[ id=" + id + " ]";
  }

}
