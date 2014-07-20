package com.welovecoding.tutorial.api.v1.resource;

import static com.welovecoding.tutorial.api.v1.RestConfig.JSON_MEDIATYPE;
import com.welovecoding.tutorial.api.v1.dto.CategoryDTO;
import com.welovecoding.tutorial.api.v1.mapping.DTOMapper;
import com.welovecoding.tutorial.data.category.Category;
import com.welovecoding.tutorial.data.category.CategoryService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("")
@Stateless
public class CategoryResource {

  @EJB
  private CategoryService categoryService;

  // http://welovecoding.com/rest/service/v1/categories
  // http://localhost:8080/wlc-webapp/rest/fhb/v1/categories
  public CategoryResource() {
  }

  @RESTCache(genericTypeHint = "com.welovecoding.tutorial.api.v1.dto.CategoryDTO")
  @GET
  @Path("categories")
  @Produces(JSON_MEDIATYPE)
  public List<CategoryDTO> getCategories() {
    List<Category> categories = categoryService.findAllOrderedByName();
    return DTOMapper.mapCategories(categories);
  }
}
