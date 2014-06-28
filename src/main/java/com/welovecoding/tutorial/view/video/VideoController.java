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
  private String playlistSlug;
  private Playlist latestPlaylist;

  @PostConstruct
  public void init() {
    setItem(new Video());
  }

  @Override
  public String edit() {
    super.edit();
    setItem(new Video());
    return Pages.ADMIN_VIDEOS;
  }

  @Override
  public String remove() {
    super.remove();
    setItem(new Video());
    return Pages.ADMIN_VIDEOS;
  }

  @Override
  public VideoService getService() {
    return service;
  }

  public List<Video> getVideos() {
    return getLatestPlaylist().getVideos();
  }

  public Playlist getLatestPlaylist() {
    if (latestPlaylist == null) {
      latestPlaylist = playlistController.getPlaylistByCategoryAndSlug(playlistController.getLatestCategory().getId(), playlistSlug);
    }
    return latestPlaylist;
  }

  public String getPlaylistSlug() {
    return playlistSlug;
  }

  public void setPlaylistSlug(String playlistSlug) {
    this.playlistSlug = playlistSlug;
  }

}
