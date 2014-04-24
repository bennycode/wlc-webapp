package com.welovecoding.security.auth;

import com.welovecoding.config.Pages;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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
  private UserSessionBean userSessionBean;
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

  public String logout() {
    LOGGER.log(Level.INFO, "Logout");
    userSessionBean.setIsLoggedIn(false);
    return Pages.INDEX;
  }
}
