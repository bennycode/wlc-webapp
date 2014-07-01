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

    if (!user.isAdmin()) {
      // Deny Access
      String referrer = servletRequest.getRequestURL().toString();
      authSessionBean.setDeniedUrl(referrer);

      String contextPath = servletRequest.getContextPath();

      if (user.getName() == null) {
        // Send redirect and cut the chain
        servletResponse.sendRedirect(contextPath + Pages.LOGIN);
        // return is correct for redirect but breaks the GoogleLogin
//        return;
      } else {
        if (user.isAdmin()) {
          //TODO should never happen since the first 'if' already checks this?
          // Send redirect and cut the chain
          servletResponse.sendRedirect(contextPath + Pages.LOGIN);
          // return is correct for redirect but breaks the GoogleLogin
//          return;
        } else {
          // Send redirect and cut the chain
          servletResponse.sendRedirect(contextPath + Pages.PROFILE);
          // return is correct for redirect but breaks the GoogleLogin
//          return;
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
