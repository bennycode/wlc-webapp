package de.fhb.rest.v1.resource;

import de.fhb.entities.Category;
import de.fhb.rest.v1.dto.CategoryDTO;
import de.fhb.rest.v1.mapping.DTOMapper;
import de.fhb.service.CategoryService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@Stateless
public class CategoryResource {

  @EJB
  private CategoryService categoryService;

  // http://welovecoding.com/rest/service/v1/categories
  // http://localhost:8080/wlc-webapp/rest/fhb/v1/categories
  public CategoryResource() {
  }

  @RESTCache(genericTypeHint = "de.fhb.rest.v1.dto.CategoryDTO")
  @GET
  @Path("categories")
  @Produces(MediaType.APPLICATION_JSON)
  public List<CategoryDTO> getCategories() {
    List<Category> categories = categoryService.orderByName();
    return DTOMapper.mapCategories(categories);
  }
}
