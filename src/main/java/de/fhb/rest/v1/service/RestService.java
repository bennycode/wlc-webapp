package de.fhb.rest.v1.service;

import de.fhb.rest.v1.dto.CategoryDTO;
import de.fhb.rest.v1.dto.PlaylistDTO;
import de.fhb.rest.v1.dto.VideoDTO;
import de.fhb.rest.v1.mapping.ToDTOMapper;
import de.fhb.service.CategoryService;
import de.fhb.service.PlaylistService;
import de.fhb.service.VideoService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/fhb/v1")
@Stateless
@Interceptors({})
public class RestService {

  @EJB
  private CategoryService categoryService;
  @EJB
  private PlaylistService playlistService;
  @EJB
  private VideoService videoService;
  final String jsonMediaType = "application/json;charset=utf-8";
  private final ToDTOMapper toDTOMapper;

  public RestService() {
    toDTOMapper = new ToDTOMapper();
  }

  @PostConstruct
  private void init() {
  }

  @GET
  @Path("categories")
  @Produces(jsonMediaType)
  public List<CategoryDTO> getCategories() {
    List<CategoryDTO> categories = toDTOMapper.mapCategoryList(categoryService.getCategoriesOrderedByTitle());
    return categories;
  }

  @GET
  @Path("category/{id}")
  @Produces(jsonMediaType)
  public List<PlaylistDTO> getPlaylists(@PathParam("id") int id) {
    List<PlaylistDTO> playlists = toDTOMapper.mapPlaylistList(playlistService.getAllPlaylistsByCategory(id));
    return playlists;
  }

  @GET
  @Path("playlist/{id}")
  @Produces(jsonMediaType)
  public List<VideoDTO> getVideos(@PathParam("id") int id) {
    List<VideoDTO> videoList = toDTOMapper.mapVideoList(videoService.getAllVideosByPlaylist(id));
    return videoList;
  }
}
