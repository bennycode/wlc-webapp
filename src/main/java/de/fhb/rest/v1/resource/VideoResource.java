package de.fhb.rest.v1.resource;

import de.fhb.entities.Video;
import de.fhb.rest.v1.dto.VideoDTO;
import de.fhb.rest.v1.mapping.DTOMapper;
import de.fhb.service.VideoService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
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
  // http://localhost:8080/wlc-webapp/rest/fhb/v1/playlist/8
  public VideoResource() {
  }

  @RESTCache(genericTypeHint = "de.fhb.rest.v1.dto.VideoDTO")
  @GET
  @Path("playlist/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<VideoDTO> getVideos(@PathParam("id") int id) {
    List<Video> videos = videoService.getAllVideosByPlaylist(id);
    return DTOMapper.mapVideos(videos);
  }
}
