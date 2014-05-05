package com.welovecoding.tutorial.data.base;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
@EntityListeners(EntityListener.class)
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String name;

  @Temporal(value = TemporalType.TIMESTAMP)
  private Date created;

  @Temporal(value = TemporalType.TIMESTAMP)
  private Date lastModified;

  public BaseEntity() {
    this.created = new Date();
    this.lastModified = new Date();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.id);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final BaseEntity other = (BaseEntity) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
  }

}
