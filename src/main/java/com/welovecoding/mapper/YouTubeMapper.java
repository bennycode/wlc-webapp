package com.welovecoding.mapper;

import com.welovecoding.entities.Playlist;

public class YouTubeMapper {

  public static Playlist mapPlaylist(com.google.api.services.youtube.model.Playlist ytPlaylist) {
    // Map data
    Playlist playlist = new Playlist();
    playlist.setName(ytPlaylist.getSnippet().getTitle());
    playlist.setDescription(ytPlaylist.getSnippet().getDescription());

    return playlist;
  }
}
