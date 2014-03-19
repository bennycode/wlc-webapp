package de.fhb.rest.v1.resource;

import de.fhb.rest.v1.dto.CategoryDTO;
import de.fhb.rest.v1.mapping.ToDTOMapper;
import de.fhb.service.CategoryService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
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
  private final ToDTOMapper toDTOMapper;

  public CategoryResource() {
    toDTOMapper = new ToDTOMapper();
  }

  @PostConstruct
  private void init() {
  }

  @GET
  @Path("categories")
  @Produces(MediaType.APPLICATION_JSON)
  public List<CategoryDTO> getCategories() {
    List<CategoryDTO> categoryListStub = new ArrayList<>();
    for (int i = 0; i < 1; i++) {
      CategoryDTO cat = new CategoryDTO();
      cat.setId(i);
      cat.setColor("#00000" + i);
//      cat.setCreated(new Date());
//      cat.setLastModified(new Date());
      cat.setTitle("Title" + i);
//      cat.setEnabled(true);
//      cat.setSlug("Slug" + i);
//      cat.setUrl("URL" + 1);
      cat.setAvailableLanguages(new ArrayList<>(Arrays.asList(new String[]{"de", "en"})));
      categoryListStub.add(cat);
    }

    List<CategoryDTO> categories = toDTOMapper.mapCategoryList(categoryService.getCategoriesOrderedByTitle());
    return categoryListStub;
  }

}
