package de.fhb.auth;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

@WebServlet(name = "GoogleLoginServlet", urlPatterns = {"/oauth2callback"})
/**
 * @see https://code.google.com/p/google-api-java-client/wiki/OAuth2
 */
public class GoogleLoginServlet extends HttpServlet {

  private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
  private static final Gson GSON = new Gson();

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet GoogleLoginServlet</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet GoogleLoginServlet at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType(MediaType.APPLICATION_JSON);

    String tokenData = (String) request.getSession().getAttribute("token");
    Enumeration<String> e = request.getSession().getAttributeNames();
    while (e.hasMoreElements()) {
      String param = e.nextElement();
      System.out.println(param);
    }

    if (tokenData == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().print(GSON.toJson("Current user not connected."));
    } else {
      processRequest(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

}
