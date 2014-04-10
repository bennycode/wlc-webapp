package de.fhb.rest.v2.resource;

import de.fhb.rest.v2.dto.CategoryDTO;
import de.fhb.rest.v2.mapping.DTOMapper;
import de.fhb.service.CategoryService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("category")
@Stateless
public class CategoryResource2 {

  private static final Logger LOG = Logger.getLogger(CategoryResource2.class.getName());

  @Context
  HttpServletRequest request;
  @Context
  UriInfo uriInfo;
  @EJB
  private CategoryService categoryService;
  @Inject
  private PlaylistResource2 playlistResource;

  public CategoryResource2() {
  }

  @GET
  @RESTCache(genericTypeHint = "de.fhb.rest.v2.dto.CategoryDTO")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategories(@Context Request req) {

    Response resp;
    try {
      List<CategoryDTO> categoryList = DTOMapper.mapCategoryList(getServerRootAddress(), categoryService.orderByName());
      resp = Response.ok(categoryList).build();
    } catch (Exception e) {
      LOG.log(Level.SEVERE, "Exception: {0}", e);
      resp = Response.status(500).build();
    }
    return resp;
  }

  @GET
  @RESTCache(genericTypeHint = "de.fhb.rest.v2.dto.CategoryDTO")
  @Path("{categoryid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategory(@PathParam("categoryid") Long categoryid, @Context Request req) {
    Response resp;
    try {

      CategoryDTO category = DTOMapper.mapCategory(getServerRootAddress(), categoryService.find(categoryid));
      resp = Response.ok(category).build();
    } catch (Exception e) {
      LOG.log(Level.SEVERE, "Exception: {0}", e);
      resp = Response.status(500).build();
    }
    return resp;
  }

  @Path("{categoryid}/playlist")
  public PlaylistResource2 getPlaylistResource() {
    return playlistResource;
  }

  private String getServerRootAddress() {
    System.out.println("BaseURI: " + uriInfo.getBaseUri().toString());
    return uriInfo.getBaseUri().toString();
  }
}
