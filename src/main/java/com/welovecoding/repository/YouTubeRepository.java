package com.welovecoding.repository;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.welovecoding.util.StringUtils;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @see https://github.com/bennyn/wlc-webapp/issues/18
 */
public class YouTubeRepository {

  private static final Logger log = Logger.getLogger(YouTubeRepository.class.getName());
  private final YouTube youtube;

  public YouTubeRepository(YouTube youtube) {
    this.youtube = youtube;
  }

  /**
   * Example:
   * https://www.googleapis.com/youtube/v3/channels?part=contentDetails&mine=true
   *
   * @param playlistItemId
   */
  public void logChannelDetails(String playlistItemId) {
    try {
      YouTube.Channels.List channelRequest = youtube.channels().list("contentDetails");
      channelRequest.setMine(true);

      ChannelListResponse channelQuery = channelRequest.execute();
      List<Channel> channelsList = channelQuery.getItems();

      for (Channel channel : channelsList) {
        log.info(channel.getContentDetails().toPrettyString());
      }
    } catch (IOException ex) {
      Logger.getLogger(YouTubeRepository.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Example:
   * https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=PLB03EA9545DD188C3
   *
   * @see https://developers.google.com/youtube/v3/docs/playlists/list
   * @param playlistIds
   */
  public void logPlaylistInfos(String[] playlistIds) {
    try {
      YouTube.Playlists.List playlistQuery = youtube.playlists().list("id,snippet,status");
      playlistQuery.setMaxResults(50L);
      playlistQuery.setId(StringUtils.join(playlistIds, ","));

      PlaylistListResponse execute = playlistQuery.execute();
      List<Playlist> playlists = execute.getItems();

      for (Playlist playlist : playlists) {
        log.log(Level.INFO, "Title: {0}", playlist.getSnippet().getTitle());
      }
    } catch (IOException ex) {
      Logger.getLogger(YouTubeRepository.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public String getPlaylistTitle(String playlistId) {
    if (playlistId == null || playlistId.equals("")) {
      return "";
    }

    String playlistTitle = "";

    try {
      YouTube.Playlists.List playlistQuery = youtube.playlists().list("id,snippet,status");
      playlistQuery.setMaxResults(1L);
      playlistQuery.setId(playlistId);

      PlaylistListResponse execute = playlistQuery.execute();
      List<Playlist> playlists = execute.getItems();
      playlistTitle = playlists.get(0).getSnippet().getTitle();
    } catch (IOException ex) {
      Logger.getLogger(YouTubeRepository.class.getName()).log(Level.SEVERE, null, ex);
    }

    return playlistTitle;
  }

  /**
   * Note: <code>ArrayIndexOutOfBoundsException</code> is not an
   * <code>IOException</code>, that's why we catch <code>Exception</code> in
   * general.
   *
   * @param playlistId YouTube playlist ID (Example: PLF544CEEC9432BF67)
   * @return YouTube playlist object
   */
  public Playlist getPlaylist(String playlistId) {
    Playlist playlist = null;

    try {
      YouTube.Playlists.List playlistQuery = youtube.playlists().list("id,snippet,status");
      playlistQuery.setMaxResults(1L);
      playlistQuery.setId(playlistId);

      PlaylistListResponse execute = playlistQuery.execute();
      List<Playlist> playlists = execute.getItems();
      playlist = playlists.get(0);
    } catch (Exception ex) {
      Logger.getLogger(YouTubeRepository.class.getName()).log(Level.SEVERE, null, ex);
    }

    return playlist;
  }
}
