/*
 * Copyright (C) 2013 Michael Koppen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
