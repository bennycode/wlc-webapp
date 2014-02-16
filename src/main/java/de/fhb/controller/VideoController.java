package de.fhb.controller;

import de.fhb.entities.Video;
import de.fhb.service.VideoService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class VideoController extends BaseController<Video, VideoService> {

  @EJB
  private VideoService service;

  @Override
  public VideoService getService() {
    return service;
  }
}
