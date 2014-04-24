package com.welovecoding.security.auth;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class UserSessionBean implements Serializable {

  private boolean isLoggedIn;
  private String deniedUrl;
  private User user;

  @PostConstruct
  void init() {
    isLoggedIn = false;
    user = new User();
  }

  public UserSessionBean() {
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

}
