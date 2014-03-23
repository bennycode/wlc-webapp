package de.fhb.controller;

import de.fhb.entities.Video;
import de.fhb.service.VideoService;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class VideoController extends GenFormBaseController<Video, VideoService> {

  @EJB
  private VideoService service;

  @Override
  public VideoService getService() {
    return service;
  }
}
