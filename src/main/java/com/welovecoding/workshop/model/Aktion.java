package com.welovecoding.workshop.model;

import java.util.List;

public class Aktion {

  private String name;
  private double spendenZiel;
  private double spendenBetrag;
  private double bisherGespendet;
  private Konto konto;
  private long id;
  private List<Spende> spenden;

  public Aktion() {
    konto = new Konto();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSpendenZiel() {
    return spendenZiel;
  }

  public void setSpendenZiel(double spendenZiel) {
    this.spendenZiel = spendenZiel;
  }

  public double getSpendenBetrag() {
    return spendenBetrag;
  }

  public void setSpendenBetrag(double spendenBetrag) {
    this.spendenBetrag = spendenBetrag;
  }

  public double getBisherGespendet() {
    return bisherGespendet;
  }

  public void setBisherGespendet(double bisherGespendet) {
    this.bisherGespendet = bisherGespendet;
  }

  public Konto getKonto() {
    return konto;
  }

  public void setKonto(Konto konto) {
    this.konto = konto;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<Spende> getSpenden() {
    return spenden;
  }

  public void setSpenden(List<Spende> spenden) {
    this.spenden = spenden;
  }
  
  
}
