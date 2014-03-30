package de.fhb.rest.v1.resource;

import de.fhb.entities.Category;
import de.fhb.rest.v1.mapping.DTOMapper;
import de.fhb.service.CategoryService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@Stateless
@Interceptors({})
public class CategoryResource {

  @EJB
  private CategoryService categoryService;

  public CategoryResource() {
  }

  @RESTCache(genericTypeHint = "de.fhb.rest.v1.dto.Category")
  @GET
  @Path("categories")
  @Produces(MediaType.APPLICATION_JSON)
  public List<de.fhb.rest.v1.dto.Category> getCategories() {
    List<de.fhb.rest.v1.dto.Category> dtoCategories = new ArrayList<>();

    List<Category> categories = categoryService.orderByName();
    for (Category category : categories) {
      de.fhb.rest.v1.dto.Category dtoCategory = DTOMapper.mapCategory(category);
      dtoCategories.add(dtoCategory);
    }

    return dtoCategories;
  }

}
