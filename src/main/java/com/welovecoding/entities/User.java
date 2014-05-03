package com.welovecoding.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class User extends BaseEntity {

  @NotNull
  private String email;

  @OneToMany(cascade = CascadeType.ALL, targetEntity = UserCredentials.class, mappedBy = "user")
  private List<UserCredentials> credentials;

  public User() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<UserCredentials> getCredentials() {
    return credentials;
  }

  public void setCredentials(List<UserCredentials> credentials) {
    this.credentials = credentials;
  }

}
