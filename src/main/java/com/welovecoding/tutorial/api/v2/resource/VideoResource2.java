package com.welovecoding.tutorial.api.v2.resource;

import static com.welovecoding.tutorial.api.v2.RestConfig.JSON_MEDIATYPE;
import com.welovecoding.tutorial.api.v2.dto.VideoDTO;
import com.welovecoding.tutorial.api.v2.mapping.DTOMapper;
import com.welovecoding.tutorial.data.video.VideoService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("videos")
@RequestScoped
public class VideoResource2 {

  private static final Logger LOG = Logger.getLogger(VideoResource2.class.getName());

  @EJB
  private VideoService videoService;

  public VideoResource2() {
  }

  @GET
  @RESTCache(genericTypeHint = "com.welovecoding.tutorial.api.v2.dto.VideoDTO")
  @Produces(JSON_MEDIATYPE)
  public Response getVideos(@PathParam("categoryid") Long categoryid, @PathParam("playlistid") Long playlistid, @Context Request req, @Context UriInfo uriInfo) {

    Response resp;
    try {
      List<VideoDTO> videoList = null;
      if (categoryid == null) {
        videoList = DTOMapper.mapVideoList(uriInfo.getBaseUri().toString(), videoService.findAllInPlaylist(playlistid));
      } else {
        videoList = DTOMapper.mapVideoList(uriInfo.getBaseUri().toString(), videoService.findAllInCategoryAndPlaylist(categoryid, playlistid));
      }
      resp = Response.ok(videoList).build();
    } catch (Exception e) {
      LOG.log(Level.SEVERE, "Exception: {0}", e);
      resp = Response.status(500).build();
    }
    return resp;
  }

  @GET
  @RESTCache(genericTypeHint = "com.welovecoding.tutorial.api.v2.dto.VideoDTO")
  @Path("{videoid}")
  @Produces(JSON_MEDIATYPE)
  public Response getVideo(@PathParam("categoryid") Long categoryid, @PathParam("playlistid") Long playlistid, @PathParam("videoid") Long videoid, @Context Request req, @Context UriInfo uriInfo) {

    Response resp;
    try {
      VideoDTO video = null;
      if (categoryid == null) {
        if (playlistid == null) {
          //direct access
          video = DTOMapper.mapVideo(uriInfo.getBaseUri().toString(), videoService.find(videoid));
        } else {
          video = DTOMapper.mapVideo(uriInfo.getBaseUri().toString(), videoService.findInPlaylist(playlistid, videoid));
        }
      } else {
        if (playlistid == null) {
          video = DTOMapper.mapVideo(uriInfo.getBaseUri().toString(), videoService.findInCategory(categoryid, videoid));
        } else {
          video = DTOMapper.mapVideo(uriInfo.getBaseUri().toString(), videoService.findInCategoryAndPlaylist(categoryid, playlistid, videoid));
        }
      }
      resp = Response.ok(video).build();
    } catch (Exception e) {
      LOG.log(Level.SEVERE, "Exception: {0}", e);
      resp = Response.status(500).build();
    }

    return resp;
  }

}
