package de.fhb.rest.v1.resource;

import de.fhb.rest.v1.dto.PlaylistDTO;
import de.fhb.rest.v1.dto.VideoDTO;
import de.fhb.rest.v1.mapping.ToDTOMapper;
import de.fhb.service.PlaylistService;
import java.util.ArrayList;
import java.util.Date;
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
public class PlaylistResource {

  @EJB
  private PlaylistService playlistService;
  private final ToDTOMapper toDTOMapper;

  public PlaylistResource() {
    toDTOMapper = new ToDTOMapper();
  }

  @PostConstruct
  private void init() {
  }

  @GET
  @Path("category/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<PlaylistDTO> getPlaylists(@PathParam("id") int id) {
    List<PlaylistDTO> playlistListStub = new ArrayList<>();
    for (int j = 0; j < 5; j++) {
      PlaylistDTO pl = new PlaylistDTO();
      pl.setId(j);
      pl.setCreated(new Date());
      pl.setLastModified(new Date());
      pl.setCode("Code" + j);
      pl.setTitle("Title" + j);

      List<VideoDTO> vl = new ArrayList<>();
      for (int k = 0; k < 5; k++) {
        VideoDTO v = new VideoDTO();
        v.setId(k);
        v.setCreated(new Date());
        v.setLastModified(new Date());
        v.setCode("Code" + k);
        v.setDescription("Desc" + k);
        v.setTitle("Title" + k);
        vl.add(v);
      }
      pl.setVideoList(vl);
      playlistListStub.add(pl);
    }
    List<PlaylistDTO> playlists = toDTOMapper.mapPlaylistList(playlistService.getAllPlaylistsByCategory(id));
    return playlistListStub;
  }
}
