package de.fhb.rest.v1.mapping;

import de.fhb.entities.Category;
import de.fhb.entities.Ebook;
import de.fhb.entities.LanguageEntry;
import de.fhb.entities.Playlist;
import de.fhb.rest.v1.dto.Status;
import de.fhb.entities.Video;
import de.fhb.rest.v1.dto.CategoryDTO;
import de.fhb.rest.v1.dto.EbookDTO;
import de.fhb.rest.v1.dto.LanguageDTO;
import de.fhb.rest.v1.dto.OwnerDTO;
import de.fhb.rest.v1.dto.PlaylistDTO;
import de.fhb.rest.v1.dto.ProviderDTO;
import de.fhb.rest.v1.dto.StatusDTO;
import de.fhb.rest.v1.dto.VideoDTO;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Provider;

/**
 *
 * @author MacYser
 */
public class ToDTOMapper {

  private boolean checkDept(int dept) {
    return dept <= 0;
  }

  private CategoryDTO mapCategoryDept(Category e, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    dept--;
    CategoryDTO dto = new CategoryDTO();
    // TODO Mapping of Category to CategoryDTO
    /*
     dto.setId(e.getId());
     dto.setTitle(e.getTitle());
     dto.setUrl(e.getUrl());
     dto.setSlug(e.getSlug());
     dto.setNumberOfVideos(e.getNumberOfVideos());
     dto.setColor(e.getColor());
     dto.setCreated(e.getCreated());
     dto.setLastModified(e.getLastModified());
     dto.setPlaylistList(this.mapPlaylistListDept(e.getPlaylistList(), dept));
     List<String> temp = new ArrayList<String>();
     for (LanguageEntry lang : e.getAvailableLanguages()) {
     temp.add(lang.getTitle());
     }
     dto.setAvailableLanguages(temp);
     */
    return dto;
  }

  private PlaylistDTO mapPlaylistDept(Playlist e, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    dept--;
    PlaylistDTO dto = new PlaylistDTO();
    // TODO Mapping of Playlist to PlaylistDTO
    /*
     dto.setId(e.getId());
     //    dto.setCategory(null);//Note this is null to prevent circular dependencies
     dto.setCategoryName(e.getCategory().getTitle());
     dto.setCode(e.getCode());
     dto.setCreated(e.getCreated());
     dto.setDescription(e.getDescription());
     dto.setLanguage(e.getLanguage().getTitle());
     dto.setLanguageId(this.mapLanguageDept(e.getLanguage(), dept));
     dto.setLastModified(e.getLastModified());
     dto.setNumberOfVideos(e.getNumberOfVideos());
     dto.setOwner(this.mapOwner(e.getOwner()));
     dto.setProvider(this.mapProviderDept(e.getProvider(), dept));
     dto.setProviderName(e.getProvider().getName());
     dto.setSlug(e.getSlug());
     dto.setStatus(this.mapStatusDept(e.getStatus(), dept));
     dto.setTitle(e.getTitle());
     dto.setVideoList(this.mapVideoListDept(e.getVideoList(), dept));
     dto.setDifficulty(e.getDifficulty());
     */
    return dto;
  }

  private LanguageDTO mapLanguageDept(LanguageEntry e, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    dept--;
    LanguageDTO dto = new LanguageDTO();
    // TODO Mapping of Language to LanguageDTO
    /*
     dto.setId(e.getId());
     dto.setCreated(e.getCreated());
     //		dto.setEbookList(null);//Note this is null to prevent circular dependencies
     //		dto.setGlossaryEntryList(null);//Note this is null to prevent circular dependencies
     dto.setLastModified(e.getLastModified());
     dto.setName(e.getTitle());
     */
    return dto;
  }

  private OwnerDTO mapOwnerDept(Owner e, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    dept--;
    OwnerDTO dto = new OwnerDTO();
    // TODO Mapping of Owner to OwnerDTO
    /*
     dto.setId(e.getId());
     dto.setCreated(e.getCreated());
     dto.setChannelUrl(e.getChannelUrl());
     dto.setDescription(e.getDescription());
     //    dto.setPlaylistList(null);//Note this is null to prevent circular dependencies
     dto.setType(e.getType());
     dto.setWebsite(e.getWebsite());
     dto.setLastModified(e.getLastModified());
     dto.setName(e.getName());
     */
    return dto;
  }

  private ProviderDTO mapProviderDept(Provider e, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    dept--;
    ProviderDTO dto = new ProviderDTO();
    // TODO Mapping of Provider to ProviderDTO
    /*
     dto.setId(e.getId());
     dto.setCreated(e.getCreated());
     dto.setLastModified(e.getLastModified());
     //    dto.setPlaylistList(null);//Note this is null to prevent circular dependencies
     dto.setName(e.getName());
     */
    return dto;
  }

  private StatusDTO mapStatusDept(Status e, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    dept--;
    StatusDTO dto = new StatusDTO();
    // TODO Mapping of Status to StatusDTO
    /*
     dto.setIsEmbeddable(e.isEmbeddable());
     dto.setIsErrorless(e.isErrorless());
     dto.setIsPlayable(e.isPlayable());
     */
    return dto;
  }

  private EbookDTO mapEbookDept(Ebook e, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    dept--;
    EbookDTO dto = new EbookDTO();
    // TODO Mapping of Ebook to EbookDTO
    /*
     dto.setId(e.getId());
     dto.setCreated(e.getCreated());
     dto.setDownloadUrl(e.getDownloadUrl());
     dto.setFree(e.isFree());
     dto.setKindle(e.isKindle());
     dto.setLanguageId(null);//Note this is null to prevent circular dependencies
     dto.setLastModified(e.getLastModified());
     dto.setTitle(e.getTitle());
     */
    return dto;
  }

  private VideoDTO mapVideoDept(Video e, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    dept--;
    VideoDTO dto = new VideoDTO();
    // TODO Mapping of Video to VideoDTO
    /*
     dto.setId(e.getId());
     dto.setCode(e.getCode());
     dto.setCreated(e.getCreated());
     dto.setDescription(e.getDescription());
     dto.setDownloadUrl(e.getDownloadUrl());
     dto.setLastModified(e.getLastModified());
     dto.setPermalink(e.getPermalink());
     //    dto.setPlaylist(null);//Note this is null to prevent circular dependencies
     dto.setPreviewImageUrl(e.getPreviewImageUrl());
     dto.setTitle(e.getTitle());
     */
    return dto;
  }

  private List<CategoryDTO> mapCategoryListDept(List<Category> eList, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    List<CategoryDTO> dtoList = new ArrayList<CategoryDTO>();
    for (Category e : eList) {
      dtoList.add(mapCategoryDept(e, dept));
    }

    return dtoList;
  }

  private List<PlaylistDTO> mapPlaylistListDept(List<Playlist> eList, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    List<PlaylistDTO> dtoList = new ArrayList<PlaylistDTO>();
    for (Playlist e : eList) {
      dtoList.add(mapPlaylistDept(e, dept));
    }

    return dtoList;
  }

  private List<EbookDTO> mapEbookListDept(List<Ebook> eList, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    List<EbookDTO> dtoList = new ArrayList<EbookDTO>();
    for (Ebook e : eList) {
      dtoList.add(mapEbookDept(e, dept));
    }

    return dtoList;
  }

  private List<VideoDTO> mapVideoListDept(List<Video> eList, int dept) {
    if (checkDept(dept)) {
      return null;
    }
    List<VideoDTO> dtoList = new ArrayList<VideoDTO>();
    for (Video e : eList) {
      dtoList.add(mapVideoDept(e, dept));
    }

    return dtoList;
  }

  // PUBLISHED METHODS
  public CategoryDTO mapCategory(Category e) {
    return mapCategoryDept(e, 1);
  }

  public PlaylistDTO mapPlaylist(Playlist e) {
    return mapPlaylistDept(e, 2);
  }

  public LanguageDTO mapLanguage(LanguageEntry e) {
    return mapLanguageDept(e, 1);
  }

  public OwnerDTO mapOwner(Owner e) {
    return mapOwnerDept(e, 1);
  }

  public ProviderDTO mapProvider(Provider e) {
    return mapProviderDept(e, 1);
  }

  public StatusDTO mapStatus(Status e) {
    return mapStatusDept(e, 1);
  }

  public EbookDTO mapEbook(Ebook e) {
    return mapEbookDept(e, 1);
  }

  public VideoDTO mapVideo(Video e) {
    return mapVideoDept(e, 1);
  }

  public List<CategoryDTO> mapCategoryList(List<Category> eList) {

    List<CategoryDTO> dtoList = new ArrayList<CategoryDTO>();
    for (Category e : eList) {
      dtoList.add(mapCategory(e));
    }

    return dtoList;
  }

  public List<PlaylistDTO> mapPlaylistList(List<Playlist> eList) {
    List<PlaylistDTO> dtoList = new ArrayList<PlaylistDTO>();
    for (Playlist e : eList) {
      dtoList.add(mapPlaylist(e));
    }

    return dtoList;
  }

  private List<EbookDTO> mapEbookList(List<Ebook> eList) {
    List<EbookDTO> dtoList = new ArrayList<EbookDTO>();
    for (Ebook e : eList) {
      dtoList.add(mapEbook(e));
    }

    return dtoList;
  }

  public List<VideoDTO> mapVideoList(List<Video> eList) {
    List<VideoDTO> dtoList = new ArrayList<VideoDTO>();
    for (Video e : eList) {
      dtoList.add(mapVideo(e));
    }

    return dtoList;
  }
}
