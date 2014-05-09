package com.welovecoding.tutorial.data.playlist.entity;

public enum Provider {

  CHANNEL9("Channel9"),
  VIMEO("Vimeo"),
  YOUTUBE("YouTube");

  private final String label;

  private Provider(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public String toString() {
    return label;
  }

}
