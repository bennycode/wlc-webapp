package com.welovecoding.web.security;

import de.fhb.config.Pages;
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

  public AdminPagesFilter() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {

    boolean isAdmin = userSessionBean.isLoggedIn();

    if (!isAdmin) {
      // Deny Access
      String contextPath = ((HttpServletRequest) request).getContextPath();
      ((HttpServletResponse) response).sendRedirect(contextPath + Pages.LOGIN);
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
