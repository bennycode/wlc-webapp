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
  private AuthSessionBean authSessionBean;

  public AdminPagesFilter() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest servletRequest = (HttpServletRequest) request;
    HttpServletResponse servletResponse = (HttpServletResponse) response;

    User user = authSessionBean.getUser();
    System.out.println(user.getName());

    if (!user.isAdmin()) {
      // Deny Access
      String referrer = servletRequest.getRequestURL().toString();
      authSessionBean.setDeniedUrl(referrer);

      // Send redirect
      String contextPath = servletRequest.getContextPath();

      if (user.getName() == null) {
        servletResponse.sendRedirect(contextPath + Pages.LOGIN);
      } else {
        if (user.isAdmin()) {
          servletResponse.sendRedirect(contextPath + Pages.LOGIN);
        } else {
          servletResponse.sendRedirect(contextPath + Pages.PROFILE);
        }
      }

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
