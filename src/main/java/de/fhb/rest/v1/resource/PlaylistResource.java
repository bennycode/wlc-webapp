package de.fhb.rest.v1.resource;

import de.fhb.entities.Playlist;
import de.fhb.rest.v1.mapping.DTOMapper;
import de.fhb.service.PlaylistService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.ArrayList;
import java.util.List;
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

  // http://welovecoding.com/rest/service/v1/category/1
  // http://localhost:8080/wlc_webapp/rest/fhb/v1/categories
  public PlaylistResource() {
  }

  @RESTCache(genericTypeHint = "de.fhb.rest.v1.dto.Playlist")
  @GET
  @Path("category/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<de.fhb.rest.v1.dto.Playlist> getPlaylists(@PathParam("id") int id) {

    List<de.fhb.rest.v1.dto.Playlist> dtoPlaylists = new ArrayList<>();

    List<Playlist> playlists = playlistService.getAllPlaylistsByCategory(id);
    for (Playlist playlist : playlists) {
      de.fhb.rest.v1.dto.Playlist dtoPlaylist = DTOMapper.mapPlaylist(playlist);
      dtoPlaylists.add(dtoPlaylist);
    }

    return dtoPlaylists;
  }
}
