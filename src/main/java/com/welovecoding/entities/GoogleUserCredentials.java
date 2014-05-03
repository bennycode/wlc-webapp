package com.welovecoding.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GOOGLE")
public class GoogleUserCredentials extends UserCredentials {
  
  public GoogleUserCredentials() {
  }
  
  public GoogleUserCredentials(String token) {
    super.setToken(token);
  }
}
