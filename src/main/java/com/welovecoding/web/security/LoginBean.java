package com.welovecoding.web.security;

import de.fhb.navigation.Pages;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * http://stackoverflow.com/a/10691832/451634
 *
 * @author Benny
 */
@Named
@RequestScoped
public class LoginBean {

  @Inject
  private UserSessionBean userSessionBean;
  private static final Logger LOGGER = Logger.getLogger(LoginBean.class.getName());
  private static final String[] users = {
    "anna:qazwsx",
    "kate:123456"
  };
  private String username;
  private String password;

  public LoginBean() {
  }

  private void saveLogin() {
    LOGGER.log(Level.INFO, "User logged-in successfully. Saving login to the session...");
    userSessionBean.setIsLoggedIn(true);
  }

  private void removeLogin() {
    userSessionBean.setIsLoggedIn(false);
  }

  public String login() {
    for (String user : users) {
      String dbUsername = user.split(":")[0];
      String dbPassword = user.split(":")[1];

      if (dbUsername.equals(username) && dbPassword.equals(password)) {
        saveLogin();
        return Pages.ADMIN_POST_LOGIN;
      }
    }

    FacesMessage message = new FacesMessage("Login error!", "ERROR MSG");
    message.setSeverity(FacesMessage.SEVERITY_ERROR);
    FacesContext.getCurrentInstance().addMessage(null, message);

    return Pages.ADMIN_LOGIN;
  }

  public String logout() {
    LOGGER.log(Level.INFO, "Logout");
    removeLogin();
    return Pages.INDEX;
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
}
