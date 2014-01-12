package com.welovecoding.workshop.model;

public class Spende {

  private double betrag;
  private String spenderName;
  private boolean quittung;
  private Status status;
  private Konto konto;

  public enum Status {

    UEBERWIESEN, IN_BEARBEITUNG;
  }

  public Spende() {
    this.konto = new Konto();
  }

  public double getBetrag() {
    return betrag;
  }

  public void setBetrag(double betrag) {
    this.betrag = betrag;
  }

  public String getSpenderName() {
    return spenderName;
  }

  public void setSpenderName(String spenderName) {
    this.spenderName = spenderName;
  }

  public boolean isQuittung() {
    return quittung;
  }

  public void setQuittung(boolean quittung) {
    this.quittung = quittung;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Konto getKonto() {
    return konto;
  }

  public void setKonto(Konto konto) {
    this.konto = konto;
  }
}
