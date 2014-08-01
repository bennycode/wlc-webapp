package com.welovecoding.tutorial.api;

import static com.welovecoding.tutorial.view.Pages.REST_FHB;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map.Entry;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;

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
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //  Create Get request dynamically to remote server
    String forwardUri = request.getRequestURL().toString();
    forwardUri = forwardUri.replaceAll("/wlc-webapp", "");
    forwardUri = forwardUri.replaceAll("/rest/", "/fhb-tutorien/rest/");
    if (request.getQueryString() != null) {
      forwardUri = forwardUri + "?" + request.getQueryString();
    }
    URL obj = new URL(forwardUri);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    // optional default is GET
    con.setRequestMethod("GET");

    //add request header
    con.setRequestProperty("User-Agent", USER_AGENT);

    int responseCode = con.getResponseCode();
    System.out.println("\nSending 'GET' request to URL : " + forwardUri);
    System.out.println("Response Code : " + responseCode);

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response1 = new StringBuffer();

    ServletOutputStream sout = response.getOutputStream();

    while ((inputLine = in.readLine()) != null) {
      response1.append(inputLine);
      sout.write(inputLine.getBytes());
    }
    in.close();

    sout.flush();

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //  Create Post request dynamically to remote server

    String forwardUri = request.getRequestURL().toString();
    forwardUri = forwardUri.replaceAll("/wlc-webapp", "");
    forwardUri = forwardUri.replaceAll("/rest/", "/fhb-tutorien/rest/");

    URL obj = new URL(forwardUri);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    //add reuqest header
    con.setRequestMethod("POST");
    con.setRequestProperty("User-Agent", USER_AGENT);
    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

    StringBuilder sb = new StringBuilder();
    for (Entry<String, String[]> e : request.getParameterMap().entrySet()) {
      if (sb.length() > 0) {
        sb.append('&');
      }
      String[] temp = e.getValue();
      for (String s : temp) {
        sb.append(URLEncoder.encode(e.getKey(), "UTF-8")).append('=').append(URLEncoder.encode(s, "UTF-8"));
      }
    }

    String urlParameters = sb.toString();

    // Send post request
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes(urlParameters);
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    System.out.println("\nSending 'POST' request to URL : " + forwardUri);
    System.out.println("Post parameters : " + urlParameters);
    System.out.println("Response Code : " + responseCode);

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response1 = new StringBuffer();

    ServletOutputStream sout = response.getOutputStream();

    while ((inputLine = in.readLine()) != null) {
      response1.append(inputLine);
      sout.write(inputLine.getBytes());
    }
    in.close();

    sout.flush();
  }

//  @Override
//  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    processRequest(req, resp);
//  }
//
//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    processRequest(req, resp);
//  }
  private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String forwardUri = req.getRequestURI();
    forwardUri = forwardUri.replaceAll("/wlc-webapp", "");
    forwardUri = forwardUri.replaceAll("/rest/", "/fhb-tutorien/rest/");

    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardUri);
//    resp.sendRedirect(redirectUri);
    dispatcher.forward(req, resp);
  }

}
