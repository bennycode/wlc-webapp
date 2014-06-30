package com.welovecoding.tutorial.api;

import static com.welovecoding.tutorial.view.Pages.REST_FHB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Koppen
 */
@WebServlet(REST_FHB + "/*")
public class FHBRestRedirectServlet extends HttpServlet {

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    processRequest(req, resp);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    processRequest(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    processRequest(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    processRequest(req, resp);
  }

  private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String redirectUri = req.getRequestURI();
    redirectUri = redirectUri.replaceAll("/wlc-webapp", "");
    redirectUri = redirectUri.replaceAll("/rest/", "/fhb-tutorien/rest/");

    resp.sendRedirect(redirectUri);
  }

}
