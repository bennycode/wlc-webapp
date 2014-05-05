package com.welovecoding.tutorial.api.v2.dto;

import java.util.List;

public class ProviderDTO extends BaseDTO {

  private List<Long> playlists;

  public ProviderDTO() {
  }

  public List<Long> getPlaylists() {
    return playlists;
  }

  public void setPlaylists(List<Long> playlists) {
    this.playlists = playlists;
  }

}
