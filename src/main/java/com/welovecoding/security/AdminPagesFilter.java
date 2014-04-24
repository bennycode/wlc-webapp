package com.welovecoding.security;

import com.welovecoding.security.auth.UserSessionBean;
import com.welovecoding.config.Pages;
import com.welovecoding.security.auth.User;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPagesFilter implements Filter {

  @Inject
  private UserSessionBean userSessionBean;

  private static final String[] ALLOWED_EMAILS = {
    "apfelbenny@googlemail.com",
    "michael.koppen@googlemail.com"
  };

  public AdminPagesFilter() {
  }

  private boolean isAllowed() {
    boolean isAllowed = false;
    User user = userSessionBean.getUser();

    if (user != null && user.getEmail() != null) {
      for (String email : ALLOWED_EMAILS) {
        if (user.getEmail().equals(email)) {
          isAllowed = true;
        }
      }
    }

    return isAllowed;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest servletRequest = (HttpServletRequest) request;
    HttpServletResponse servletResponse = (HttpServletResponse) response;

    if (!isAllowed()) {
      // Deny Access
      String referrer = servletRequest.getRequestURL().toString();
      userSessionBean.setDeniedUrl(referrer);

      // Send redirect
      String contextPath = servletRequest.getContextPath();
      servletResponse.sendRedirect(contextPath + Pages.LOGIN);
    }

    // Continue filtering...
    chain.doFilter(request, response);
  }

  @Override
  public void init(FilterConfig config) throws ServletException {
    // Nothing to do here!
  }

  @Override
  public void destroy() {
    // Nothing to do here!
  }
}
