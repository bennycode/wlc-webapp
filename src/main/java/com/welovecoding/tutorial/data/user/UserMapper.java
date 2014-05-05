package com.welovecoding.tutorial.data.user;

import com.welovecoding.tutorial.data.user.entity.User;

public class UserMapper {

  public static User convertGoogleUser(GoogleUser gu) {
    User user = new User();
    user.setEmail(gu.getEmail());
    user.setName(gu.getGivenName() + " " + gu.getFamilyName());
    return user;
  }
}
