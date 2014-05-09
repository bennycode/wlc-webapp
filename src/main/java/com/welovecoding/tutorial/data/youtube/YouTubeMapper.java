package com.welovecoding.tutorial.data.youtube;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.PlaylistItem;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.data.video.Video;

public class YouTubeMapper {

  public static Video mapVideo(PlaylistItem ytVideo) {
    Video video = new Video();

    video.setName(ytVideo.getSnippet().getTitle());
    video.setDescription(ytVideo.getSnippet().getDescription());
    video.setCode(ytVideo.getContentDetails().getVideoId());
    // TODO: Convert DateTime to Date
    DateTime publishedAt = ytVideo.getSnippet().getPublishedAt();

    return video;
  }

  public static void updatePlaylist(Playlist playlist, com.google.api.services.youtube.model.Playlist ytPlaylist) {
    playlist.setCode(ytPlaylist.getId());
    playlist.setName(ytPlaylist.getSnippet().getTitle());
    playlist.setDescription(ytPlaylist.getSnippet().getDescription());
  }
}
