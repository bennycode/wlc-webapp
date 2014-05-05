package com.welovecoding.tutorial.view.auth;

import com.google.api.client.auth.oauth2.Credential;
import com.welovecoding.tutorial.data.user.entity.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AuthSessionBean implements Serializable {

  private boolean isLoggedIn;
  private String deniedUrl;
  private User user;
  // TODO: Put this into UserCredentials.java
  private Credential credential;

  @PostConstruct
  void init() {
    isLoggedIn = false;
    user = new User();
  }

  public AuthSessionBean() {
  }

  public boolean isLoggedIn() {
    return isLoggedIn;
  }

  public void setIsLoggedIn(boolean isLoggedIn) {
    this.isLoggedIn = isLoggedIn;
  }

  public String getDeniedUrl() {
    return deniedUrl;
  }

  public void setDeniedUrl(String deniedUrl) {
    this.deniedUrl = deniedUrl;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Credential getCredential() {
    return credential;
  }

  public void setCredential(Credential credential) {
    this.credential = credential;
  }

}
