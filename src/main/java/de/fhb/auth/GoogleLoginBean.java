package de.fhb.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import de.fhb.util.JSFUtils;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class GoogleLoginBean implements Serializable {

  // https://console.developers.google.com/project/apps~we-love-coding/apiui/credential
  private static final String CLIENT_ID = "173259066390.apps.googleusercontent.com";
  private static final String REGISTERED_REDIRECT_URI = "/oauth2callback";
  private static final List<String> SCOPES = Arrays.asList(
          "https://www.googleapis.com/auth/userinfo.email",
          "https://www.googleapis.com/auth/userinfo.profile"
  );

  public String buildLoginUrl() {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    String redirectUri = JSFUtils.getBaseURL(externalContext) + REGISTERED_REDIRECT_URI;

    GoogleAuthorizationCodeRequestUrl authUrl
            = new GoogleAuthorizationCodeRequestUrl(CLIENT_ID, redirectUri, SCOPES);

    return authUrl.setState("/profile").build();
  }
}
