package de.fhb.rest.v1.resource;

import de.fhb.entities.Video;
import de.fhb.rest.v1.mapping.DTOMapper;
import de.fhb.service.VideoService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@Stateless
public class VideoResource {

  @EJB
  private VideoService videoService;

  // http://welovecoding.com/rest/service/v1/playlist/8
  public VideoResource() {
  }

  @RESTCache(genericTypeHint = "de.fhb.rest.v1.dto.Video")
  @GET
  @Path("playlist/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<de.fhb.rest.v1.dto.Video> getVideos(@PathParam("id") int id) {
    List<de.fhb.rest.v1.dto.Video> dtoVideos = new ArrayList<>();

    List<Video> videos = videoService.getAllVideosByPlaylist(id);
    for (Video video : videos) {
      de.fhb.rest.v1.dto.Video dtoVideo = DTOMapper.mapVideo(video);
      dtoVideos.add(dtoVideo);
    }

    return dtoVideos;
  }
}
