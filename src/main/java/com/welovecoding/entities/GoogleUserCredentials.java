package com.welovecoding.entities;

import static com.welovecoding.entities.GoogleUserCredentials.CREDENTIAL_TYPE_COLUMN_VALUE;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(CREDENTIAL_TYPE_COLUMN_VALUE)
public class GoogleUserCredentials extends UserCredentials {

  public static final String CREDENTIAL_TYPE_COLUMN_VALUE = "CRED_TYPE";

  public GoogleUserCredentials() {
  }

  public GoogleUserCredentials(String token) {
    super.setToken(token);
  }
}
