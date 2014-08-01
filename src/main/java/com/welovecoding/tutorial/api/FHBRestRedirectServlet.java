package com.welovecoding.tutorial.api;

import static com.welovecoding.tutorial.view.Pages.REST_FHB;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
  public void init() throws ServletException {
    try {
      super.init();
      // Create a trust manager that does not validate certificate chains
      TrustManager[] trustAllCerts;
      trustAllCerts = new TrustManager[]{new X509TrustManager() {
        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
          return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
        }
      }
      };

      // Install the all-trusting trust manager
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

      // Create all-trusting host name verifier
      HostnameVerifier allHostsValid = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
          return true;
        }
      };
      HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    } catch (KeyManagementException | NoSuchAlgorithmException ex) {
      Logger.getLogger(FHBRestRedirectServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

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

  private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String forwardUri = req.getRequestURL().toString();
    forwardUri = forwardUri.replaceAll("/wlc-webapp", "");
    forwardUri = forwardUri.replaceAll("/rest/", "/fhb-tutorien/rest/");
    if (req.getQueryString() != null) {
      forwardUri = forwardUri + "?" + req.getQueryString();
    }

    URL obj = new URL(forwardUri);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    //add reuqest header
    con.setRequestMethod(req.getMethod());
    System.out.println("Headers: ");
    for (String header : Collections.list(req.getHeaderNames())) {
      System.out.println(header + ": " + req.getHeader(header));
      if (!"cookie".equalsIgnoreCase(header)) {
        con.setRequestProperty(header, req.getHeader(header));
      }
    }

//    System.out.println("Attributes: ");
//    for (String attributes : Collections.list(req.getAttributeNames())) {
//      System.out.println(attributes);
//    }
    String urlParameters = null;
    if (req.getMethod().equalsIgnoreCase("post")) {
      StringBuilder sb = new StringBuilder();
      for (Entry<String, String[]> e : req.getParameterMap().entrySet()) {
        if (sb.length() > 0) {
          sb.append('&');
        }
        String[] temp = e.getValue();
        for (String s : temp) {
          sb.append(URLEncoder.encode(e.getKey(), "UTF-8")).append('=').append(URLEncoder.encode(s, "UTF-8"));
        }
      }

      urlParameters = sb.toString();

      // Send post request
      con.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(con.getOutputStream());
      wr.writeBytes(urlParameters);
      wr.flush();
      wr.close();
    }

    int responseCode = con.getResponseCode();
    System.out.println("\nSending '" + req.getMethod() + "' request to URL : " + forwardUri);
    System.out.println("Post parameters : " + urlParameters);
    System.out.println("Response Code : " + responseCode);

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuilder response1 = new StringBuilder();

    ServletOutputStream sout = resp.getOutputStream();

    while ((inputLine = in.readLine()) != null) {
      response1.append(inputLine);
      sout.write(inputLine.getBytes());
    }
    in.close();

    sout.flush();
  }

}
