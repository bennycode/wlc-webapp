package de.fhb.rest.v1.mapping;

import de.fhb.entities.Author;
import de.fhb.entities.Category;
import de.fhb.entities.LanguageCode;
import de.fhb.entities.Playlist;
import de.fhb.entities.Video;
import de.fhb.rest.v1.dto.AuthorDTO;
import de.fhb.rest.v1.dto.CategoryDTO;
import de.fhb.rest.v1.dto.PlaylistDTO;
import de.fhb.rest.v1.dto.StatusDTO;
import de.fhb.rest.v1.dto.VideoDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This mapper maps the latest entites of the domain model to data transfer
 * objects for the first version of the REST service.
 *
 * Example URLs of the first REST service:
 * http://welovecoding.com/rest/service/v1/categories
 * http://welovecoding.com/rest/service/v1/category/1
 * http://welovecoding.com/rest/service/v1/playlist/8
 *
 * @author Benny
 */
public class DTOMapper {

  public static AuthorDTO mapAuthor(Author author) {
    AuthorDTO dtoAuthor = new AuthorDTO();

    dtoAuthor.setDescription(author.getDescription());
    dtoAuthor.setName(author.getName());
    dtoAuthor.setWebsite(author.getWebsite());

    return dtoAuthor;
  }

  public static PlaylistDTO mapPlaylist(Playlist playlist) {
    PlaylistDTO dtoPlaylist = new PlaylistDTO();

    dtoPlaylist.setId(playlist.getId());
    dtoPlaylist.setName(playlist.getName());
    dtoPlaylist.setLanguage(mapLanguage(playlist.getLanguageCode()));
    dtoPlaylist.setCategoryName(playlist.getCategory().getName());
    dtoPlaylist.setProviderName(playlist.getProvider().toString());
    dtoPlaylist.setNumberOfVideos(playlist.getVideos().size());
    dtoPlaylist.setDescription(playlist.getDescription());
    dtoPlaylist.setOwner(mapAuthor(playlist.getAuthor()));
    dtoPlaylist.setStatus(new StatusDTO());
    if (playlist.getDifficulty() != null) {
      dtoPlaylist.setDifficulty(String.valueOf(playlist.getDifficulty()));
    }

    return dtoPlaylist;
  }

  public static List<PlaylistDTO> mapPlaylists(List<Playlist> playlists) {
    List<PlaylistDTO> dtoPlaylists = new ArrayList<>();

    for (Playlist playlist : playlists) {
      PlaylistDTO dtoPlaylist = DTOMapper.mapPlaylist(playlist);
      dtoPlaylists.add(dtoPlaylist);
    }

    return dtoPlaylists;
  }

  public static String mapLanguage(LanguageCode language) {
    String dtoLanguage = "English";

    if (language != null) {
      switch (language) {
        case EN:
          dtoLanguage = "English";
          break;
        case DE:
          dtoLanguage = "German";
          break;
      }
    }

    return dtoLanguage;
  }

  public static CategoryDTO mapCategory(Category category) {
    CategoryDTO dtoCategory = new CategoryDTO();

    dtoCategory.setId(category.getId());
    dtoCategory.setName(category.getName());
    dtoCategory.setColor(category.getColor());

    HashMap<String, String> availableLanguagesMap = new HashMap<>();
    List<String> availableLanguages = new ArrayList<>();
    int numberOfVideos = 0;

    if (category.getPlaylists().size() > 0) {
      for (Playlist playlist : category.getPlaylists()) {
        numberOfVideos += playlist.getVideos().size();
        availableLanguagesMap.put(
                mapLanguage(playlist.getLanguageCode()),
                mapLanguage(playlist.getLanguageCode())
        );
      }
    }

    dtoCategory.setNumberOfVideos(numberOfVideos);

    SortedSet<String> sortedKeys = new TreeSet<>(availableLanguagesMap.keySet());
    for (String key : sortedKeys) {
      availableLanguages.add(key);
    }

    dtoCategory.setAvailableLanguages(availableLanguages);

    return dtoCategory;
  }

  public static List<CategoryDTO> mapCategories(List<Category> categories) {
    List<de.fhb.rest.v1.dto.CategoryDTO> dtoCategories = new ArrayList<>();

    for (Category category : categories) {
      de.fhb.rest.v1.dto.CategoryDTO dtoCategory = DTOMapper.mapCategory(category);
      dtoCategories.add(dtoCategory);
    }

    return dtoCategories;
  }

  public static VideoDTO mapVideo(Video video) {
    VideoDTO dtoVideo = new VideoDTO();

    dtoVideo.setId(video.getId());
    dtoVideo.setName(video.getName());
    dtoVideo.setDescription(video.getDescription());
    dtoVideo.setCode(video.getCode());
    dtoVideo.setPreviewImageUrl(video.getPreviewImageUrl());
    dtoVideo.setDownloadUrl(video.getDownloadUrl());
    dtoVideo.setPermalink(video.getPermalink());

    return dtoVideo;
  }

  public static List<VideoDTO> mapVideos(List<Video> videos) {
    List<VideoDTO> dtoVideos = new ArrayList<>();

    for (Video video : videos) {
      VideoDTO dtoVideo = DTOMapper.mapVideo(video);
      dtoVideos.add(dtoVideo);
    }

    return dtoVideos;
  }
}
