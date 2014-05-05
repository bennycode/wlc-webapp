package com.welovecoding.security.auth;

import com.welovecoding.entities.User;
import com.welovecoding.security.auth.google.GoogleUser;


public class UserConverter {

  public static User convertGoogleUser(GoogleUser gu) {
    User user = new User();
    user.setEmail(gu.getEmail());
    user.setName(gu.getGivenName() + " " + gu.getFamilyName());
    return user;
  }
}
