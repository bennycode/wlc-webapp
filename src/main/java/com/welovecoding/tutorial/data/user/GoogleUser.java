package com.welovecoding.tutorial.data.user;

public class GoogleUser {

  private String id;
  private String email;
  private boolean verifiedEmail;
  private String name;
  private String givenName;
  private String familyName;
  private String link;
  private String picture;
  private String gender;
  private String locale;

  public GoogleUser() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isVerifiedEmail() {
    return verifiedEmail;
  }

  public void setVerifiedEmail(boolean verifiedEmail) {
    this.verifiedEmail = verifiedEmail;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

}
