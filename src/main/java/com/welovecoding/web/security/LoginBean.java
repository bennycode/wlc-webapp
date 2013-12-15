package com.welovecoding.web.security;

import com.welovecoding.web.navigation.Pages;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginBean implements Serializable {

  private static final String[] users = {
    "anna:qazwsx",
    "kate:123456"
  };

  private String username;
  private String password;

  private boolean loggedIn;

  public String doLogin() {
    for (String user : users) {
      String dbUsername = user.split(":")[0];
      String dbPassword = user.split(":")[1];

      if (dbUsername.equals(username) && dbPassword.equals(password)) {
        loggedIn = true;
        return Pages.ADMIN_INDEX.toString();
      }
    }

    FacesMessage message = new FacesMessage("Login error!", "ERROR MSG");
    message.setSeverity(FacesMessage.SEVERITY_ERROR);
    FacesContext.getCurrentInstance().addMessage(null, message);

    return Pages.LOGIN.toString();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }

  public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }

}
