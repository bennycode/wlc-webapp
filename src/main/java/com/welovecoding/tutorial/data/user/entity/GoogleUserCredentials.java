package com.welovecoding.tutorial.data.user.entity;

import com.welovecoding.tutorial.data.user.entity.UserCredentials;
import static com.welovecoding.tutorial.data.user.entity.GoogleUserCredentials.CREDENTIAL_TYPE_COLUMN_VALUE;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(CREDENTIAL_TYPE_COLUMN_VALUE)
public class GoogleUserCredentials extends UserCredentials {

  public static final String CREDENTIAL_TYPE_COLUMN_VALUE = "GOOGLE";

  public GoogleUserCredentials() {
  }

  public GoogleUserCredentials(String token) {
    super.setToken(token);
  }
}
