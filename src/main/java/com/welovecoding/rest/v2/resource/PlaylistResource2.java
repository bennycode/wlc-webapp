package com.welovecoding.rest.v2.resource;

import static com.welovecoding.rest.v2.RestConfig.JSON_MEDIATYPE;
import com.welovecoding.rest.v2.dto.PlaylistDTO;
import com.welovecoding.rest.v2.mapping.DTOMapper;
import com.welovecoding.service.PlaylistService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("playlists")
@RequestScoped
public class PlaylistResource2 {

  private static final Logger LOG = Logger.getLogger(PlaylistResource2.class.getName());

  @EJB
  private PlaylistService playlistService;
  @Inject
  private VideoResource2 videoResource;

  public PlaylistResource2() {
  }

  @GET
  @RESTCache(genericTypeHint = "com.welovecoding.rest.v2.dto.PlaylistDTO")
  @Path("{playlistid}")
  @Produces(JSON_MEDIATYPE)
  public Response getPlaylist(@PathParam("categoryid") Long categoryid, @PathParam("playlistid") Long playlistid, @Context Request req, @Context UriInfo uriInfo) {

    Response resp;
    try {
      PlaylistDTO playlist = null;
      if (categoryid == null) {
        playlist = DTOMapper.mapPlaylist(uriInfo.getBaseUri().toString(), playlistService.find(playlistid));
      } else {
        playlist = DTOMapper.mapPlaylist(uriInfo.getBaseUri().toString(), playlistService.findInCategory(categoryid, playlistid));
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

}
