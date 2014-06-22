package com.welovecoding.tutorial.view.video;

import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.data.video.Video;
import com.welovecoding.tutorial.data.video.VideoService;
import com.welovecoding.tutorial.view.Pages;
import com.welovecoding.tutorial.view.playlist.PlaylistController;
import com.welovecoding.tutorial.view.scaffolding.GenFormBaseController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class VideoController extends GenFormBaseController<Video, VideoService> {

  @Inject
  private PlaylistController playlistController;
  @EJB
  private VideoService service;
  private long playlistId;

  @PostConstruct
  public void init() {
    this.item = new Video();
  }

  @Override
  public String edit() {
    super.edit();
    this.item = new Video();
    return Pages.ADMIN_VIDEOS;
  }

  @Override
  public String remove() {
    super.remove();
    this.item = new Video();
    return Pages.ADMIN_VIDEOS;
  }

  @Override
  public VideoService getService() {
    return service;
  }

  public List<Video> getVideos() {
    return getService().getVideosByPlaylistId(playlistId);
  }

  public Playlist getLatestPlaylist() {
    return playlistController.getPlaylist(playlistId);
  }

  public long getPlaylistId() {
    return playlistId;
  }

  public void setPlaylistId(long playlistId) {
    this.playlistId = playlistId;
  }

}
