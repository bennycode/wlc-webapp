package com.welovecoding.tutorial.data.user.entity;

import com.welovecoding.tutorial.data.base.BaseEntity;
import static com.welovecoding.tutorial.data.user.entity.User.FIND_BY_EMAIL;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
  @NamedQuery(name = FIND_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User extends BaseEntity {

  public static final String FIND_BY_EMAIL = "User.findByEmail";

  @NotNull @Column(unique = true)
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
