package com.welovecoding.web.security;

import com.welovecoding.web.navigation.Pages;
import com.welovecoding.web.registration.UserSessionBean;
import com.welovecoding.web.session.SessionValues;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LoginBean implements Serializable {

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
    context.getExternalContext().getSessionMap().put(SessionValues.LOGGED_IN, true);
     UserSessionBean userSessionBean = new UserSessionBean();
     userSessionBean.setLoggedIn(true);
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
