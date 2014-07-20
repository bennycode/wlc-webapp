package com.welovecoding.tutorial.api.v2.resource;

import static com.welovecoding.tutorial.api.v2.RestConfig.JSON_MEDIATYPE;
import com.welovecoding.tutorial.api.v2.dto.CategoryDTO;
import com.welovecoding.tutorial.api.v2.mapping.DTOMapper;
import com.welovecoding.tutorial.data.category.CategoryService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.List;
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

@Path("categories")
@RequestScoped
public class CategoryResource2 {

  private static final Logger LOG = Logger.getLogger(CategoryResource2.class.getName());

  @EJB
  private CategoryService categoryService;
  @Inject
  private PlaylistResource2 playlistResource;

  public CategoryResource2() {
  }

  @GET
  
  @RESTCache(genericTypeHint = "com.welovecoding.tutorial.api.v2.dto.CategoryDTO")
  @Produces(JSON_MEDIATYPE)
  public Response getCategories(@Context Request req, @Context UriInfo uriInfo) {

    Response resp;
    try {
      List<CategoryDTO> categoryList = DTOMapper.mapCategoryList(uriInfo.getBaseUri().toString(), categoryService.findAllOrderedByName());
      resp = Response.ok(categoryList).build();
    } catch (Exception e) {
      LOG.log(Level.SEVERE, "Exception: {0}", e);
      resp = Response.status(500).build();
    }
    return resp;
  }

  @GET
  
  @RESTCache(genericTypeHint = "com.welovecoding.tutorial.api.v2.dto.CategoryDTO")
  @Path("{categoryid}")
  @Produces(JSON_MEDIATYPE)
  public Response getCategory(@PathParam("categoryid") Long categoryid, @Context Request req, @Context UriInfo uriInfo) {
    Response resp;
    try {

      CategoryDTO category = DTOMapper.mapCategory(uriInfo.getBaseUri().toString(), categoryService.find(categoryid));
      resp = Response.ok(category).build();
    } catch (Exception e) {
      LOG.log(Level.SEVERE, "Exception: {0}", e);
      resp = Response.status(500).build();
    }
    return resp;
  }

  @Path("{categoryid}/playlists")
  public PlaylistResource2 getPlaylistResource() {
    return playlistResource;
  }

}
