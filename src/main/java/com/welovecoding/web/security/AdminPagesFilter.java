package com.welovecoding.web.security;

import com.welovecoding.web.navigation.Pages;
import com.welovecoding.web.session.SessionValues;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminPagesFilter implements Filter {

	@Inject
	private UserSessionBean userSessionBean;
	private static final Logger LOGGER = Logger.getLogger(AdminPagesFilter.class.getName());

	public AdminPagesFilter() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

//		HttpSession session = ((HttpServletRequest) request).getSession();

		boolean isAdmin = false;
		if (userSessionBean != null) {
			isAdmin = userSessionBean.isLoggedIn();
		}


//		Object attribute = session.getAttribute(SessionValues.LOGGED_IN);
//		if (attribute != null) {
//			LOGGER.log(Level.INFO, "There is an attribute called {0}.", SessionValues.LOGGED_IN);
//			if (attribute.equals("yes")) {
//				isAdmin = true;
//			}
//		}

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
