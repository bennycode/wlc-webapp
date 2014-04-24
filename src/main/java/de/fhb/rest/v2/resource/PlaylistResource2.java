package de.fhb.rest.v2.resource;

import de.fhb.rest.v2.dto.PlaylistDTO;
import de.fhb.rest.v2.mapping.DTOMapper;
import de.fhb.service.PlaylistService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("playlists")
@Stateless
public class PlaylistResource2 {

  private static final Logger LOG = Logger.getLogger(PlaylistResource2.class.getName());

  @Context
  UriInfo uriInfo;
  @EJB
  private PlaylistService playlistService;
  @Inject
  private VideoResource2 videoResource;

  public PlaylistResource2() {
  }

  @GET
  @RESTCache(genericTypeHint = "de.fhb.rest.v2.dto.PlaylistDTO")
  @Path("{playlistid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPlaylist(@PathParam("categoryid") Long categoryid, @PathParam("playlistid") Long playlistid, @Context Request req) {

    Response resp;
    try {
      PlaylistDTO playlist = null;
      if (categoryid == null) {
        playlist = DTOMapper.mapPlaylist(getServerRootAddress(), playlistService.find(playlistid));
      } else {
        playlist = DTOMapper.mapPlaylist(getServerRootAddress(), playlistService.findInCategory(categoryid, playlistid));
      }
      resp = Response.ok(playlist).build();
    } catch (Exception e) {
      LOG.log(Level.SEVERE, "Exception: {0}", e);
      resp = Response.status(500).build();
    }

    return resp;
  }

  @Path("{playlistid}/videos")
  public VideoResource2 getVideoResource() {
    return videoResource;
  }

  private String getServerRootAddress() {
    return uriInfo.getBaseUri().toString();
  }
}
