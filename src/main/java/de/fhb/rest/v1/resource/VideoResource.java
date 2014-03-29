package de.fhb.rest.v1.resource;

import de.fhb.rest.v1.dto.VideoDTO;
import de.fhb.rest.v1.mapping.ToDTOMapper;
import de.fhb.service.VideoService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@Stateless
@Interceptors({})
public class VideoResource {

  @EJB
  private VideoService videoService;
  private final ToDTOMapper toDTOMapper;

  public VideoResource() {
    toDTOMapper = new ToDTOMapper();
  }

  @PostConstruct
  private void init() {
  }

  @RESTCache(genericTypeHint = "de.fhb.rest.v1.dto.VideoDTO")
  @GET
  @Path("playlist/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<VideoDTO> getVideos(@PathParam("id") int id) {
    List<VideoDTO> videoListStub = new ArrayList<>();
    for (int k = 0; k < 1; k++) {
      VideoDTO v = new VideoDTO();
      v.setId(k);
//          v.setCreated(new Date());
//          v.setLastModified(new Date());
      v.setCode("Code" + k);
      v.setDescription("Desc" + k);
      v.setTitle("Title" + k);
      v.setDownloadUrl("DownloadURL" + k);
      v.setPermalink("PermaLink" + k);
      //    v.setPlaylist(null);//Note this is null to prevent circular dependencies
      v.setPreviewImageUrl("PreviewImageURL" + k);

      videoListStub.add(v);

    }
    List<VideoDTO> videoList = toDTOMapper.mapVideoList(videoService.getAllVideosByPlaylist(id));
    return videoListStub;
  }
}
