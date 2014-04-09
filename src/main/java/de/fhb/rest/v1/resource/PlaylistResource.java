package de.fhb.rest.v1.resource;

import de.fhb.entities.Playlist;
import de.fhb.rest.v1.dto.PlaylistDTO;
import de.fhb.rest.v1.mapping.DTOMapper;
import de.fhb.service.PlaylistService;
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
public class PlaylistResource {

  @EJB
  private PlaylistService playlistService;

  // http://welovecoding.com/rest/service/v1/category/1
  // http://localhost:8080/wlc-webapp/rest/fhb/v1/category/1
  public PlaylistResource() {
  }

  @RESTCache(genericTypeHint = "de.fhb.rest.v1.dto.PlaylistDTO")
  @GET
  @Path("category/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<PlaylistDTO> getPlaylists(@PathParam("id") int id) {
    List<Playlist> playlists = playlistService.getAllByCategory(id);
    return DTOMapper.mapPlaylists(playlists);
  }
}
