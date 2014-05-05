package com.welovecoding.tutorial.view.auth.google;

import com.welovecoding.tutorial.data.user.GoogleUser;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.welovecoding.tutorial.view.Pages;
import com.welovecoding.tutorial.data.user.entity.GoogleUserCredentials;
import com.welovecoding.tutorial.data.user.entity.User;
import com.welovecoding.tutorial.data.user.entity.UserCredentials;
import com.welovecoding.tutorial.data.ConstraintViolationBagException;
import com.welovecoding.tutorial.data.user.UserMapper;
import com.welovecoding.tutorial.view.auth.AuthSessionBean;
import com.welovecoding.tutorial.data.user.UserService;
import com.welovecoding.tutorial.view.auth.AuthSessionBean;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GoogleLoginServlet", urlPatterns = {"/oauth2callback"})
/**
 * @see https://code.google.com/p/google-api-java-client/wiki/OAuth2
 */
public class GoogleLoginServlet extends HttpServlet {

  public static final String CODE_URL_PARAM_NAME = "code";
  public static final String ERROR_URL_PARAM_NAME = "error";
  public static final String URL_MAPPING = "/oauth2callback";

  @Inject
  private GoogleLoginBean googleLoginBean;

  @Inject
  private AuthSessionBean userSessionBean;

  @EJB
  private UserService userService;

  @Override
  /**
   * TODO: Catch: oauth2callback?error=access_denied&state=/profile
   *
   * @see
   * https://developers.google.com/google-apps/tasks/oauth-authorization-callback-handler?hl=de
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Getting the "error" URL parameter
    String[] error = request.getParameterValues(ERROR_URL_PARAM_NAME);

    // Checking if there was an error such as the user denied access
    if (error != null && error.length > 0) {
      response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "There was an error: \"" + error[0] + "\".");
      return;
    }
    // Getting the "code" URL parameter
    String[] code = request.getParameterValues(CODE_URL_PARAM_NAME);

    // Checking conditions on the "code" URL parameter
    if (code == null || code.length == 0) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The \"code\" URL parameter is missing");
    } else {
      GoogleTokenResponse tokenResponse = googleLoginBean.convertCodeToToken(code[0]);
      String accessToken = tokenResponse.getIdToken();
      System.out.println("Access Token: " + accessToken);

      GoogleUser gu = googleLoginBean.getUser(tokenResponse);
      User userEntity = userService.findByEmail(gu.getEmail());
      GoogleUserCredentials credentials;
      boolean isFirstToken = false;

      if (userEntity == null) {
        userEntity = UserMapper.convertGoogleUser(gu);
        isFirstToken = true;
      } else {
        List<UserCredentials> userCredentials = userEntity.getCredentials();
        if (userCredentials != null && userCredentials.size() > 0) {
          for (UserCredentials c : userCredentials) {
            if (c.getCredType() == null) {
              isFirstToken = true;
            } else if (c.getCredType().equals(GoogleUserCredentials.CREDENTIAL_TYPE_COLUMN_VALUE)) {
              credentials = (GoogleUserCredentials) c;
              credentials.setToken(code[0]);
            }
          }
        } else {
          isFirstToken = true;
        }
      }

      // Token creation
      if (isFirstToken) {
        credentials = new GoogleUserCredentials(code[0]);
        credentials.setUser(userEntity);
        userEntity.setCredentials(Arrays.asList(new UserCredentials[]{credentials}));
      }

      try {
        // Save user in database
        userService.edit(userEntity);
      } catch (ConstraintViolationBagException ex) {
        Logger.getLogger(GoogleLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
      }

      // Save user in session
      userSessionBean.setUser(userEntity);

      String redirectUrl = userSessionBean.getDeniedUrl();
      if (redirectUrl == null) {
        LOG.log(Level.INFO, "Redirect URL not found.");
        String contextPath = this.getServletContext().getContextPath();
        redirectUrl = contextPath + Pages.ADMIN_INDEX + ".xhtml";
      }
      LOG.log(Level.INFO, "Redirecting to: {0}", redirectUrl);

      response.sendRedirect(redirectUrl);
    }
  }
  private static final Logger LOG = Logger.getLogger(GoogleLoginServlet.class.getName());

  /**
   * Construct the OAuth code callback handler URL.
   *
   * @param req the HttpRequest object
   * @return The constructed request's URL
   */
  public static String getOAuthCodeCallbackHandlerUrl(HttpServletRequest req) {
    String scheme = req.getScheme() + "://";
    String serverName = req.getServerName();
    String serverPort = (req.getServerPort() == 80) ? "" : ":" + req.getServerPort();
    String contextPath = req.getContextPath();
    String servletPath = URL_MAPPING;
    String pathInfo = (req.getPathInfo() == null) ? "" : req.getPathInfo();
    return scheme + serverName + serverPort + contextPath + servletPath + pathInfo;
  }
}
