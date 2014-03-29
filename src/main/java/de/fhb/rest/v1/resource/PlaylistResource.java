package de.fhb.rest.v1.resource;

import de.fhb.rest.v1.dto.LanguageDTO;
import de.fhb.rest.v1.dto.OwnerDTO;
import de.fhb.rest.v1.dto.PlaylistDTO;
import de.fhb.rest.v1.dto.StatusDTO;
import de.fhb.rest.v1.mapping.ToDTOMapper;
import de.fhb.service.PlaylistService;
import de.yser.ownsimplecache.util.jaxrs.RESTCache;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@Stateless
@Interceptors({})
public class PlaylistResource {

  @EJB
  private PlaylistService playlistService;
  private final ToDTOMapper toDTOMapper;

  public PlaylistResource() {
    toDTOMapper = new ToDTOMapper();
  }

  @PostConstruct
  private void init() {
  }

  @RESTCache(genericTypeHint = "de.fhb.rest.v1.dto.PlaylistDTO")
  @GET
  @Path("category/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<PlaylistDTO> getPlaylists(@PathParam("id") int id) {
    List<PlaylistDTO> playlistListStub = new ArrayList<>();
    for (int j = 0; j < 1; j++) {
      PlaylistDTO pl = new PlaylistDTO();
      pl.setId(j);
//        pl.setCreated(new Date());
//        pl.setLastModified(new Date());
//        pl.setCode("Code" + j);
      pl.setTitle("Title" + j);
//        pl.setCategoryId(null);//Note this is null to prevent circular dependencies
      pl.setCategoryName("CategoryTitle");
      pl.setDescription("Desc" + j);
//        pl.setDifficulty(Difficulty.EASY);
//        pl.setEnabled(true);
      pl.setLanguage("Deutsch");
      pl.setLanguageId(new LanguageDTO(j, "Deutsch"));
      pl.setNumberOfVideos(5);
      pl.setOwner(new OwnerDTO(j, "OwnerName" + j));
//        pl.setProvider(new ProviderDTO(i, "ProviderName" + i));
      pl.setProviderName("ProviderName" + j);
//        pl.setSlug("Slug" + j);
      pl.setStatus(new StatusDTO());

      playlistListStub.add(pl);
    }
    List<PlaylistDTO> playlists = toDTOMapper.mapPlaylistList(playlistService.getAllPlaylistsByCategory(id));
    return playlistListStub;
  }
}
