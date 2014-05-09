package com.welovecoding.tutorial.data.youtube;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.welovecoding.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @see https://github.com/bennyn/wlc-webapp/issues/18
 */
public class YouTubeRepository {

  private static final Logger log = Logger.getLogger(YouTubeRepository.class.getName());
  private final YouTube youtube;
  private List<PlaylistItem> playlistItems;

  public YouTubeRepository(YouTube youtube) {
    this.youtube = youtube;
    this.playlistItems = new ArrayList<>();
  }

  public void logChannelDetails(String playlistItemId) {
    try {
      // https://www.googleapis.com/youtube/v3/channels?part=contentDetails&mine=true
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
   * @see https://developers.google.com/youtube/v3/docs/playlists/list
   * @param playlistIds
   */
  public void logPlaylistInfos(String[] playlistIds) {
    try {
      // https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=PLB03EA9545DD188C3
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

  public Playlist getVideos(String playlistId, String pageToken) {
    Playlist playlist = null;

    if (pageToken == null) {
      playlistItems = new ArrayList<>();
    }

    try {
      YouTube.PlaylistItems.List query = youtube.playlistItems().list("id,snippet,contentDetails");
      query.setPlaylistId(playlistId);
      query.setMaxResults(50L);
      if (pageToken != null) {
        query.setPageToken(pageToken);
      }

      // Execute query
      PlaylistItemListResponse response = query.execute();

      // Parse results
      for (PlaylistItem playlistItem : response.getItems()) {
        playlistItems.add(playlistItem);
        System.out.println(playlistItem.getContentDetails().getVideoId());
      }

      String nextPageToken = response.getNextPageToken();
      System.out.println("PAGE TOKEN: " + nextPageToken);

      if (nextPageToken != null) {
        getVideos(playlistId, nextPageToken);
      }

    } catch (Exception ex) {
      Logger.getLogger(YouTubeRepository.class.getName()).log(Level.SEVERE, null, ex);
    }

    return playlist;
  }
}
