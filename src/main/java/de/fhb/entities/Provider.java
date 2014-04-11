package de.fhb.entities;

public enum Provider {

  YOUTUBE("YouTube"),
  VIMEO("Vimeo"),
  CHANNEL9("Channel9");

  private String name;

  private Provider(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
