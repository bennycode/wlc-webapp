package com.welovecoding.tutorial.view.auth.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.PlusScopes;
import com.google.api.services.youtube.YouTubeScopes;
import com.welovecoding.tutorial.data.user.GoogleUser;
import com.welovecoding.tutorial.view.JSFUtils;
import com.welovecoding.tutorial.view.auth.AuthSessionBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;

@Named
@ApplicationScoped
public class GoogleLoginBean implements Serializable {

  private static final long serialVersionUID = 1L;

  // https://console.developers.google.com/project/apps~we-love-coding/apiui/credential
  private static final GoogleCredentials GOOGLE_CREDENTIALS = new GoogleCredentials();
  private static final String REGISTERED_REDIRECT_URI = "/oauth2callback";
  private static final List<String> SCOPES = Arrays.asList(
          PlusScopes.USERINFO_EMAIL,
          PlusScopes.USERINFO_PROFILE,
          YouTubeScopes.YOUTUBE_READONLY
  );

  // API
  private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
  private String redirectUri;

  // Utils
  private static final JsonFactory JSON_FACTORY = new JacksonFactory();
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final ObjectMapper mapper = new ObjectMapper();

  // WLC
  @Inject
  private AuthSessionBean userSessionBean;

  @PostConstruct
  void init() {
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    redirectUri = JSFUtils.getBaseURL(externalContext) + REGISTERED_REDIRECT_URI;
  }

  public String buildLoginUrl() {
    GoogleAuthorizationCodeRequestUrl authUrl
            = new GoogleAuthorizationCodeRequestUrl(GOOGLE_CREDENTIALS.getClientId(), redirectUri, SCOPES);

    return authUrl.setState("/profile").build();
  }

  public GoogleTokenResponse convertCodeToToken(String code) throws IOException {
    GoogleAuthorizationCodeTokenRequest request
            = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    new JacksonFactory(),
                    GOOGLE_CREDENTIALS.getClientId(),
                    GOOGLE_CREDENTIALS.getClientSecret(),
                    code,
                    redirectUri);

    return request.execute();
  }

  public GoogleUser getUser(GoogleTokenResponse response) throws IOException {
    GoogleAuthorizationCodeFlow flow
            = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT,
                    JSON_FACTORY,
                    GOOGLE_CREDENTIALS.getClientId(),
                    GOOGLE_CREDENTIALS.getClientSecret(),
                    SCOPES).build();

    Credential credential = flow.createAndStoreCredential(response, null);
    userSessionBean.setCredential(credential);
    HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);

    // Get G+ info
    GenericUrl url = new GenericUrl(USER_INFO_URL);
    HttpRequest request = requestFactory.buildGetRequest(url);

    String jsonIdentity = request.execute().parseAsString();
    return mapper.readValue(jsonIdentity, GoogleUser.class);
  }
}
