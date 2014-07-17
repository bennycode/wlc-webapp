package com.welovecoding.tutorial.view.auth;

import com.welovecoding.tutorial.view.Pages;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @see http://stackoverflow.com/a/10691832/451634
 * @see http://stackoverflow.com/a/3920184/451634
 * @author Benny
 */
@Named
@RequestScoped
public class LoginBean {

  @Inject
  private AuthSessionBean userSessionBean;
  private static final Logger LOGGER = Logger.getLogger(LoginBean.class.getName());

  public LoginBean() {
  }

  public String login() {
    if (userSessionBean.isLoggedIn()) {
      LOGGER.log(Level.INFO, "User logged-in successfully. Saving login to the session...");
      return userSessionBean.getDeniedUrl() + "?faces-redirect=true";
    } else {
      return Pages.ADMIN_LOGIN;
    }
  }

  public void logout() {
    LOGGER.log(Level.INFO, "Logout");

    // Request values
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    String referrer = externalContext.getRequestHeaderMap().get("referer");

    // Logout
    externalContext.invalidateSession();
    userSessionBean.setIsLoggedIn(false);

    // Redirect
    try {
      facesContext.getExternalContext().redirect(referrer);
    } catch (IOException ex) {
      LOGGER.log(Level.WARNING, "Cannot redirect after logout: {0}", referrer);
    }
  }
}
