package com.welovecoding.tutorial.api.v2.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BaseDTO implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long id;
  private Date created;
  private Date lastModified;

  private String name;

  public BaseDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 11 * hash + Objects.hashCode(this.id);
    hash = 11 * hash + Objects.hashCode(this.created);
    hash = 11 * hash + Objects.hashCode(this.lastModified);
    hash = 11 * hash + Objects.hashCode(this.name);
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
    final BaseDTO other = (BaseDTO) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    if (!Objects.equals(this.created, other.created)) {
      return false;
    }
    if (!Objects.equals(this.lastModified, other.lastModified)) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    return true;
  }

}
