package com.welovecoding.tutorial.view.video;

import com.welovecoding.tutorial.view.scaffolding.GenFormBaseController;
import com.welovecoding.tutorial.data.video.Video;
import com.welovecoding.tutorial.view.Pages;
import com.welovecoding.tutorial.data.video.VideoService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class VideoController extends GenFormBaseController<Video, VideoService> {

  @EJB
  private VideoService service;

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
}
