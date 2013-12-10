package com.welovecoding.web.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * @author Benny Neugebauer (bn@bennyn.de)
 */
@Entity
@Table(name = "videos")
@NamedQueries({
  @NamedQuery(name = "Videos.findAll", query = "SELECT v FROM Videos v"),
  @NamedQuery(name = "Videos.findById", query = "SELECT v FROM Videos v WHERE v.id = :id"),
  @NamedQuery(name = "Videos.findByCode", query = "SELECT v FROM Videos v WHERE v.code = :code"),
  @NamedQuery(name = "Videos.findByCreated", query = "SELECT v FROM Videos v WHERE v.created = :created"),
  @NamedQuery(name = "Videos.findByDescription", query = "SELECT v FROM Videos v WHERE v.description = :description"),
  @NamedQuery(name = "Videos.findByLastmodified", query = "SELECT v FROM Videos v WHERE v.lastmodified = :lastmodified"),
  @NamedQuery(name = "Videos.findByTitle", query = "SELECT v FROM Videos v WHERE v.title = :title")})
public class Videos implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "CODE")
  private String code;
  @Column(name = "CREATED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;
  @Size(max = 255)
  @Column(name = "DESCRIPTION")
  private String description;
  @Column(name = "LASTMODIFIED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastmodified;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "TITLE")
  private String title;
  @JoinColumn(name = "playlist_id", referencedColumnName = "ID")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private Playlist playlistId;

  public Videos() {
  }

  public Videos(Integer id) {
    this.id = id;
  }

  public Videos(Integer id, String code, String title) {
    this.id = id;
    this.code = code;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Playlist getPlaylistId() {
    return playlistId;
  }

  public void setPlaylistId(Playlist playlistId) {
    this.playlistId = playlistId;
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
    if (!(object instanceof Videos)) {
      return false;
    }
    Videos other = (Videos) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.welovecoding.web.entities.Videos[ id=" + id + " ]";
  }

}
