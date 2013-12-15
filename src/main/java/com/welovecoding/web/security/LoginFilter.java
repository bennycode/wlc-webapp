package com.welovecoding.web.security;

import com.welovecoding.web.navigation.Pages;
import com.welovecoding.web.session.SessionValues;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpSession session = ((HttpServletRequest) request).getSession();
    Object attribute = session.getAttribute(SessionValues.LOGGED_IN);

    if (attribute != null && (boolean) attribute == true) {
      // User is logged-in
    } else {
      String contextPath = ((HttpServletRequest) request).getContextPath();
      ((HttpServletResponse) response).sendRedirect(contextPath + Pages.LOGIN);
    }

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
