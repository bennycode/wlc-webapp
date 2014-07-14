package com.welovecoding.tutorial.api.v1.resource;

import static com.welovecoding.tutorial.api.v1.RestConfig.JSON_MEDIATYPE;
import com.welovecoding.tutorial.api.v1.dto.PlaylistDTO;
import com.welovecoding.tutorial.api.v1.mapping.DTOMapper;
import com.welovecoding.tutorial.data.category.Category;
import com.welovecoding.tutorial.data.category.CategoryService;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("")
@Stateless
public class PlaylistResource {

  @EJB
  private CategoryService categoryService;

  // http://welovecoding.com/rest/service/v1/category/1
  // http://localhost:8080/wlc-webapp/rest/fhb/v1/category/1
  public PlaylistResource() {
  }

  @RESTCache(genericTypeHint = "com.welovecoding.tutorial.api.v1.dto.PlaylistDTO")
  @GET
  @Path("category/{categoryid}")
  @Produces(JSON_MEDIATYPE)
  public List<PlaylistDTO> getPlaylists(@PathParam("categoryid") Long categoryid) {
    List<Playlist> playlists = new ArrayList<>();
    Category category = categoryService.find(categoryid);
    if (category != null) {
      playlists = category.getPlaylists();
    }
    return DTOMapper.mapPlaylists(playlists);
  }
}
