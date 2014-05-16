package com.welovecoding.tutorial.data.youtube;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.PlaylistItem;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.data.video.Video;

public class YouTubeMapper {

  public static Video mapVideo(PlaylistItem ytVideo) {
    Video video = new Video();
    updateVideo(video, ytVideo);
    return video;
  }

  public static void updateVideo(Video video, PlaylistItem ytVideo) {
    video.setName(ytVideo.getSnippet().getTitle());
    video.setDescription(ytVideo.getSnippet().getDescription());
    video.setCode(ytVideo.getContentDetails().getVideoId());
    // TODO: Convert DateTime to Date
    DateTime publishedAt = ytVideo.getSnippet().getPublishedAt();
  }

  public static Playlist mapPlaylist(com.google.api.services.youtube.model.Playlist ytPlaylist) {
    Playlist playlist = new Playlist();
    updatePlaylist(playlist, ytPlaylist);
    return playlist;
  }

  public static void updatePlaylist(Playlist playlist, com.google.api.services.youtube.model.Playlist ytPlaylist) {
    playlist.setCode(ytPlaylist.getId());
    playlist.setName(ytPlaylist.getSnippet().getTitle());
    playlist.setDescription(ytPlaylist.getSnippet().getDescription());
  }
}
