package com.welovecoding.tutorial.view;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Koppen
 */
//@WebFilter(filterName = "URLFilter", urlPatterns = {"/*"})
public class URLFilter implements Filter {

  // The filter configuration object we are associated with.  If
  // this value is null, this filter instance is not currently
  // configured. 
  private FilterConfig filterConfig = null;

  public URLFilter() {
  }

  private void doBeforeProcessing(ServletRequest request, ServletResponse response)
          throws IOException, ServletException {

  }

  private void doAfterProcessing(ServletRequest request, ServletResponse response)
          throws IOException, ServletException {

    HttpServletRequest httpRequest = null;
    HttpServletResponse httpResponse = null;
    if (request instanceof HttpServletRequest) {
      httpRequest = (HttpServletRequest) request;
    }
    if (response instanceof HttpServletResponse) {
      httpResponse = (HttpServletResponse) response;
    }

    if (httpRequest != null) {

      FacesContext context = null;
      if (httpResponse != null) {
        try {
          context = JSFUtils.getFacesContext(httpRequest, httpResponse);
        } catch (IllegalStateException ise) {
          System.out.println("cannot create session after resp.");
        }
      }

      String url = httpRequest.getRequestURL().toString();
      String uri = httpRequest.getRequestURI();
      String queryString = ((HttpServletRequest) request).getQueryString();
      System.out.println("Requested URL: " + url);
      if (context != null) {
        System.out.println("FacesLocale: " + context.getViewRoot().getLocale().toString());
      }
      System.out.println("URI: " + uri);
      System.out.println("QueryString: " + queryString);

//      if (uri.startsWith("/wlc-webapp/tutorials")) {
//        System.out.println("Tutorials Site");
//
//        uri = uri.replaceAll("/wlc-webapp/tutorials", "");
//        System.out.println("URI: " + uri);
//
//        Pattern pattern;
//        Matcher matcher;
//
//        if (uri.matches("/[a-z0-9-]*/[a-z0-9-]*/#[a-z0-9-]*")) {
//          // its a video
//          System.out.println("It's a Video!");
//          pattern = Pattern.compile("/[a-z0-9-]*/[a-z0-9-]*/#([a-z0-9-]*)");
//          matcher = pattern.matcher(uri);
//          while (matcher.find()) {
//            System.out.println("VideoSlug: " + matcher.group());
//          }
//        } else if (uri.matches("/[a-z0-9-]*/[a-z0-9-]*/")) {
//          // its a playlist
//          System.out.println("It's a Playlist!");
//          pattern = Pattern.compile("/[a-z0-9-]*/([a-z0-9-]*)/");
//          matcher = pattern.matcher(uri);
//          while (matcher.find()) {
//            System.out.println("PlaylistSlug: " + matcher.group());
//          }
//        } else if (uri.matches("/[a-z0-9-]*/")) {
//          // its a category
//          System.out.println("It's a Category!");
//          pattern = Pattern.compile("/([a-z0-9-]*)/");
//          matcher = pattern.matcher(uri);
//          while (matcher.find()) {
//            System.out.println("CategorySlug: " + matcher.group());
//          }
//        }
//      } else if (uri.startsWith("/wlc-webapp/rest/service/v2/categories")) {
//        System.out.println("REST Categories");
//        uri = uri.replaceAll("/wlc-webapp/rest/service/v2/categories", "");
//        System.out.println("URI: " + uri);
//      }
    }
  }

  /**
   *
   * @param request The servlet request we are processing
   * @param response The servlet response we are creating
   * @param chain The filter chain we are processing
   *
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet error occurs
   */
  public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain)
          throws IOException, ServletException {

    doBeforeProcessing(request, response);
//    Throwable problem = null;
//    try {
    chain.doFilter(request, response);
//    } catch (Throwable t) {
//      // If an exception is thrown somewhere down the filter chain,
//      // we still want to execute our after processing, and then
//      // rethrow the problem after that.
//      problem = t;
//      t.printStackTrace();
//    }

    doAfterProcessing(request, response);
    // If there was a problem, we want to rethrow it if it is
    // a known type, otherwise log it.
//    if (problem != null) {
//      if (problem instanceof ServletException) {
//        throw (ServletException) problem;
//      }
//      if (problem instanceof IOException) {
//        throw (IOException) problem;
//      }
//      sendProcessingError(problem, response);
//    }
  }

  /**
   * Return the filter configuration object for this filter.
   */
  public FilterConfig getFilterConfig() {
    return (this.filterConfig);
  }

  /**
   * Set the filter configuration object for this filter.
   *
   * @param filterConfig The filter configuration object
   */
  public void setFilterConfig(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }

  /**
   * Destroy method for this filter
   */
  public void destroy() {
  }

  /**
   * Init method for this filter
   */
  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
    if (filterConfig != null) {
    }
  }

  /**
   * Return a String representation of this object.
   */
  @Override
  public String toString() {
    if (filterConfig == null) {
      return ("URLFilter()");
    }
    StringBuffer sb = new StringBuffer("URLFilter(");
    sb.append(filterConfig);
    sb.append(")");
    return (sb.toString());
  }

  private void sendProcessingError(Throwable t, ServletResponse response) {
    String stackTrace = getStackTrace(t);

    if (stackTrace != null && !stackTrace.equals("")) {
      try {
        response.setContentType("text/html");
        PrintStream ps = new PrintStream(response.getOutputStream());
        PrintWriter pw = new PrintWriter(ps);
        pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

        // PENDING! Localize this for next official release
        pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
        pw.print(stackTrace);
        pw.print("</pre></body>\n</html>"); //NOI18N
        pw.close();
        ps.close();
        response.getOutputStream().close();
      } catch (Exception ex) {
      }
    } else {
      try {
        PrintStream ps = new PrintStream(response.getOutputStream());
        t.printStackTrace(ps);
        ps.close();
        response.getOutputStream().close();
      } catch (Exception ex) {
      }
    }
  }

  public static String getStackTrace(Throwable t) {
    String stackTrace = null;
    try {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      pw.close();
      sw.close();
      stackTrace = sw.getBuffer().toString();
    } catch (Exception ex) {
    }
    return stackTrace;
  }

  public void log(String msg) {
    filterConfig.getServletContext().log(msg);
  }

  // Wrap the protected FacesContext.setCurrentInstance() in a inner class.
  private static abstract class FacesContextWrapper extends FacesContext {

    protected static void setCurrentInstance(FacesContext facesContext) {
      FacesContext.setCurrentInstance(facesContext);
    }
  }
}
