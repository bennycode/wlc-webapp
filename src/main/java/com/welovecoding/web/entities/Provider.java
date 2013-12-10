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
@Table(name = "providers")
@NamedQueries({
  @NamedQuery(name = "Provider.findAll", query = "SELECT p FROM Provider p"),
  @NamedQuery(name = "Provider.findById", query = "SELECT p FROM Provider p WHERE p.id = :id"),
  @NamedQuery(name = "Provider.findByCreated", query = "SELECT p FROM Provider p WHERE p.created = :created"),
  @NamedQuery(name = "Provider.findByLastmodified", query = "SELECT p FROM Provider p WHERE p.lastmodified = :lastmodified"),
  @NamedQuery(name = "Provider.findByName", query = "SELECT p FROM Provider p WHERE p.name = :name")})
public class Provider implements Serializable {
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
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider", fetch = FetchType.EAGER)
  private List<Playlist> playlistList;

  public Provider() {
  }

  public Provider(Integer id) {
    this.id = id;
  }

  public Provider(Integer id, String name) {
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
    if (!(object instanceof Provider)) {
      return false;
    }
    Provider other = (Provider) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.welovecoding.web.entities.Provider[ id=" + id + " ]";
  }

}
