package com.welovecoding.workshop.model;

public class Konto {

  private String name;
  private String nameDerBank;
  private String kontoNr;
  private String blz;

  public Konto() {
  }

  public Konto(String name, String nameDerBank, String kontoNr, String blz) {
    this.name = name;
    this.nameDerBank = nameDerBank;
    this.kontoNr = kontoNr;
    this.blz = blz;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameDerBank() {
    return nameDerBank;
  }

  public void setNameDerBank(String nameDerBank) {
    this.nameDerBank = nameDerBank;
  }

  public String getKontoNr() {
    return kontoNr;
  }

  public void setKontoNr(String kontoNr) {
    this.kontoNr = kontoNr;
  }

  public String getBlz() {
    return blz;
  }

  public void setBlz(String blz) {
    this.blz = blz;
  }
}
