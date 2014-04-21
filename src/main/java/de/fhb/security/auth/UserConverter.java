package de.fhb.security.auth;

import de.fhb.security.auth.google.GoogleUser;

public class UserConverter {

  public static User convertGoogleUser(GoogleUser gu) {
    User user = new User();
    user.setEmail(gu.getEmail());
    user.setName(gu.getGivenName() + " " + gu.getFamilyName());
    return user;
  }
}
