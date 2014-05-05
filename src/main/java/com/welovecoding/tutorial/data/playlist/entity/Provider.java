package com.welovecoding.tutorial.data.playlist.entity;

public enum Provider {

  YOUTUBE,
  VIMEO,
  CHANNEL9;

  @Override
  public String toString() {
    switch (this) {
      case CHANNEL9:
        return "Channel9";
      case VIMEO:
        return "Vimeo";
      default:
        return "YouTube";
    }
  }

}
