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
import javax.persistence.Lob;
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
@Table(name = "owners")
@NamedQueries({
  @NamedQuery(name = "Owner.findAll", query = "SELECT o FROM Owner o"),
  @NamedQuery(name = "Owner.findById", query = "SELECT o FROM Owner o WHERE o.id = :id"),
  @NamedQuery(name = "Owner.findByChannelUrl", query = "SELECT o FROM Owner o WHERE o.channelUrl = :channelUrl"),
  @NamedQuery(name = "Owner.findByCreated", query = "SELECT o FROM Owner o WHERE o.created = :created"),
  @NamedQuery(name = "Owner.findByLastmodified", query = "SELECT o FROM Owner o WHERE o.lastmodified = :lastmodified"),
  @NamedQuery(name = "Owner.findByName", query = "SELECT o FROM Owner o WHERE o.name = :name"),
  @NamedQuery(name = "Owner.findByType", query = "SELECT o FROM Owner o WHERE o.type = :type"),
  @NamedQuery(name = "Owner.findByWebsite", query = "SELECT o FROM Owner o WHERE o.website = :website")})
public class Owner implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Size(max = 255)
  @Column(name = "channel_url")
  private String channelUrl;
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
  @Column(name = "NAME")
  private String name;
  @Column(name = "TYPE")
  private Integer type;
  @Size(max = 255)
  @Column(name = "WEBSITE")
  private String website;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId", fetch = FetchType.EAGER)
  private List<Playlist> playlistList;

  public Owner() {
  }

  public Owner(Integer id) {
    this.id = id;
  }

  public Owner(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getChannelUrl() {
    return channelUrl;
  }

  public void setChannelUrl(String channelUrl) {
    this.channelUrl = channelUrl;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
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
    if (!(object instanceof Owner)) {
      return false;
    }
    Owner other = (Owner) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.welovecoding.web.entities.Owner[ id=" + id + " ]";
  }

}
