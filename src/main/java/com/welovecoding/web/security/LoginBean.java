package com.welovecoding.web.security;

import com.welovecoding.web.navigation.Pages;
import com.welovecoding.web.registration.UserSessionBean;
import com.welovecoding.web.session.SessionValues;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * http://stackoverflow.com/a/10691832/451634
 * <p>
 * </p>
 * @author Benny
 */
@Named
@RequestScoped
public class LoginBean {
  @Inject
  HttpServletRequest request;

  private static final Logger LOGGER = Logger.getLogger(LoginBean.class.getName());

  private final FacesContext context;

  private static final String[] users = {
    "anna:qazwsx",
    "kate:123456"
  };

  private String username;
  private String password;

  public LoginBean() {
    context = FacesContext.getCurrentInstance();
  }

  private void saveLoginInSession() {
    String get = (String) context.getExternalContext().getSessionMap().get(SessionValues.LOGGED_IN);
    LOGGER.log(Level.INFO, "Schon was da: {0}", get);

    LOGGER.log(Level.INFO, "User logged-in successfully. Saving login to the session...");
    context.getExternalContext().getSessionMap().put(SessionValues.LOGGED_IN, "yes");
  }

  public String login() {
    for (String user : users) {
      String dbUsername = user.split(":")[0];
      String dbPassword = user.split(":")[1];

      if (dbUsername.equals(username) && dbPassword.equals(password)) {
        saveLoginInSession();
        return Pages.ADMIN_INDEX;
      }
    }

    FacesMessage message = new FacesMessage("Login error!", "ERROR MSG");
    message.setSeverity(FacesMessage.SEVERITY_ERROR);
    FacesContext.getCurrentInstance().addMessage(null, message);

    return Pages.JSF_LOGIN;
  }

  public String logout() {
    context.getExternalContext().invalidateSession();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    return request.getContextPath() + Pages.INDEX;
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

  boolean isLoggedIn() {
    return (boolean) context.getExternalContext().getSessionMap().get(SessionValues.LOGGED_IN);
  }

}
