package com.welovecoding.tutorial.view.auth;

import com.welovecoding.tutorial.data.user.entity.User;
import com.welovecoding.tutorial.view.Pages;
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
  private AuthSessionBean userSessionBean;

  public AdminPagesFilter() {
  }

  private boolean isAllowed() {
    User user = userSessionBean.getUser();

    return user.isAdmin();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest servletRequest = (HttpServletRequest) request;
    HttpServletResponse servletResponse = (HttpServletResponse) response;

    System.out.println("User is allowed: " + isAllowed());

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
    // NOP
  }

  @Override
  public void destroy() {
    // NOP
  }
}
