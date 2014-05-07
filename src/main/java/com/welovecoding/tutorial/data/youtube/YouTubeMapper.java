package com.welovecoding.tutorial.data.youtube;

import com.welovecoding.tutorial.data.playlist.entity.Playlist;

public class YouTubeMapper {

  public static void updatePlaylist(Playlist playlist, com.google.api.services.youtube.model.Playlist ytPlaylist) {
    playlist.setCode(ytPlaylist.getId());
    playlist.setName(ytPlaylist.getSnippet().getTitle());
    playlist.setDescription(ytPlaylist.getSnippet().getDescription());
  }
}
