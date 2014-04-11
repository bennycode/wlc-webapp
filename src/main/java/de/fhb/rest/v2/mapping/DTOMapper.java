package de.fhb.rest.v2.mapping;

import static de.fhb.config.Pages.REST_CATEGORY;
import static de.fhb.config.Pages.REST_PLAYLIST;
import static de.fhb.config.Pages.REST_VIDEO;
import de.fhb.entities.Author;
import de.fhb.entities.Category;
import de.fhb.entities.Language;
import de.fhb.entities.Playlist;
import de.fhb.entities.Provider;
import de.fhb.entities.Video;
import de.fhb.rest.v2.dto.AuthorDTO;
import de.fhb.rest.v2.dto.CategoryDTO;
import de.fhb.rest.v2.dto.PlaylistDTO;
import de.fhb.rest.v2.dto.StatusDTO;
import de.fhb.rest.v2.dto.VideoDTO;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author MacYser
 */
public class DTOMapper {

  public static CategoryDTO mapCategory(String root, Category e) {
    if (e != null) {
      CategoryDTO dto = new CategoryDTO();
      dto.setId(e.getId());
      dto.setName(e.getName());
      dto.setSlug(e.getSlug());
      dto.setColor(e.getColor());
      dto.setCreated(e.getCreated());
      dto.setLastModified(e.getLastModified());
      dto.setPlaylists(mapToPlaylistAddressList(root, e.getPlaylists()));
      Set<String> tempLang = new HashSet<>();
      int tempNumOfVideos = 0;
      for (Playlist playlist : e.getPlaylists()) {

        tempNumOfVideos = tempNumOfVideos + playlist.getVideos().size();
        tempLang.add(mapLanguage(playlist.getLanguageCode()));
      }
      dto.setLanguageCodes(tempLang);
      dto.setNumberOfVideos(tempNumOfVideos);
      return dto;
    } else {
      return null;
    }

  }

  private static String mapCategoryAddress(String root, Category e) {
    if (e != null) {
      System.out.println("CategoryAddressTemplate: " + root + REST_CATEGORY);
      return MessageFormat.format(root + REST_CATEGORY, new Object[]{
        e.getId()});
    } else {
      return null;
    }

  }

  public static List<CategoryDTO> mapCategoryList(String root, List<Category> eList) {

    List<CategoryDTO> dtoList = new ArrayList<>();
    for (Category e : eList) {
      dtoList.add(mapCategory(root, e));
    }

    return dtoList;
  }

  private static List<String> mapToPlaylistAddressList(String root, List<Playlist> eList) {
    List<String> dtoList = new ArrayList<>();
    for (Playlist e : eList) {
      dtoList.add(mapPlaylistAddress(root, e));
    }

    return dtoList;
  }

  public static PlaylistDTO mapPlaylist(String root, Playlist e) {
    if (e != null) {
      PlaylistDTO dto = new PlaylistDTO();
      dto.setId(e.getId());
      dto.setCategory(mapCategoryAddress(root, e.getCategory()));
      dto.setDescription(e.getDescription());
      dto.setLanguageCode(mapLanguage(e.getLanguageCode()));
      dto.setCreated(e.getCreated());
      dto.setLastModified(e.getLastModified());
      dto.setNumberOfVideos(e.getVideos().size());
      dto.setAuthor(mapAuthor(e.getAuthor()));
      dto.setProvider(e.getProvider().getName());
//    dto.setSlug(e.getSlug());
      dto.setStatus(new StatusDTO());
      dto.setName(e.getName());
      dto.setVideos(mapVideoAddressList(root, e.getVideos()));
      return dto;
    } else {
      return null;
    }

  }

  private static String mapPlaylistAddress(String root, Playlist e) {
    if (e != null) {
      System.out.println("PlaylistAddressTemplate: " + root + REST_PLAYLIST);
      return MessageFormat.format(root + REST_PLAYLIST, new Object[]{
        e.getCategory().getId(),
        e.getId()});
    } else {
      return null;
    }

  }

  public static List<PlaylistDTO> mapPlaylistList(String root, List<Playlist> eList) {
    List<PlaylistDTO> dtoList = new ArrayList<>();
    for (Playlist e : eList) {
      dtoList.add(mapPlaylist(root, e));
    }

    return dtoList;
  }

  private static AuthorDTO mapAuthor(Author e) {
    if (e != null) {
      AuthorDTO dto = new AuthorDTO();

      dto.setId(e.getId());
      dto.setCreated(e.getCreated());
      dto.setChannelUrl(e.getChannelUrl());
      dto.setDescription(e.getDescription());
      dto.setWebsite(e.getWebsite());
      dto.setLastModified(e.getLastModified());
      dto.setName(e.getName());

      return dto;
    } else {
      return null;
    }

  }

  public static VideoDTO mapVideo(String root, Video e) {
    if (e != null) {
      VideoDTO dto = new VideoDTO();
      dto.setId(e.getId());
      dto.setCode(e.getCode());
      dto.setCreated(e.getCreated());
      dto.setDescription(e.getDescription());
      dto.setDownloadUrl(e.getDownloadUrl());
      dto.setLastModified(e.getLastModified());
      dto.setPermalink(e.getPermalink());
      dto.setPlaylist(mapPlaylistAddress(root, e.getPlaylist()));
      dto.setPreviewImageUrl(e.getPreviewImageUrl());
      dto.setName(e.getName());
      return dto;
    } else {
      return null;
    }

  }

  private static String mapVideoAddress(String root, Video e) {
    if (e != null) {
      System.out.println("VideoAddressTemplate: " + root + REST_VIDEO);
      return MessageFormat.format(root + REST_VIDEO, new Object[]{
        e.getPlaylist().getCategory().getId(),
        e.getPlaylist().getId(),
        e.getId()});
    } else {
      return null;
    }

  }

  public static List<VideoDTO> mapVideoList(String root, List<Video> eList) {
    List<VideoDTO> dtoList = new ArrayList<>();
    for (Video e : eList) {
      dtoList.add(mapVideo(root, e));
    }

    return dtoList;
  }

  public static List<String> mapVideoAddressList(String root, List<Video> eList) {
    List<String> dtoList = new ArrayList<>();
    for (Video e : eList) {
      dtoList.add(mapVideoAddress(root, e));
    }

    return dtoList;
  }

  public static String mapLanguage(Language e) {
    String lang = "English";

    if (e.getLanguageCode() != null) {
      switch (e.getLanguageCode()) {
        case Language.LanguageCode.GERMAN:
          lang = "German";
          break;
        case Language.LanguageCode.ENGLISH:
          lang = "English";
          break;
      }
    }

    return lang;
  }
}
