package de.fhb.rest.v1.mapping;

import de.fhb.entities.Language.LanguageCode;
import de.fhb.rest.v1.dto.Category;
import de.fhb.rest.v1.dto.Owner;
import de.fhb.rest.v1.dto.Playlist;
import de.fhb.rest.v1.dto.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

  public static Owner mapAuthor(de.fhb.entities.Author author) {
    Owner dtoAuthor = new Owner();

    dtoAuthor.setDescription(author.getDescription());
    dtoAuthor.setName(author.getName());
    dtoAuthor.setWebsite(author.getWebsite());

    return dtoAuthor;
  }

  public static Playlist mapPlaylist(de.fhb.entities.Playlist playlist) {
    Playlist dtoPlaylist = new Playlist();

    dtoPlaylist.setId(playlist.getId());
    dtoPlaylist.setName(playlist.getName());
    dtoPlaylist.setLanguage(mapLanguage(playlist.getLanguageCode()));
    dtoPlaylist.setCategoryName(playlist.getCategory().getName());
    dtoPlaylist.setProviderName(playlist.getProviderName().getProviderName());
    dtoPlaylist.setNumberOfVideos(playlist.getVideos().size());
    dtoPlaylist.setDescription(playlist.getDescription());
    dtoPlaylist.setOwner(mapAuthor(playlist.getAuthor()));
    dtoPlaylist.setStatus(new Status());

    return dtoPlaylist;
  }

  public static String mapLanguage(de.fhb.entities.Language language) {
    String dtoLanguage = "English";

    if (language.getLanguageCode() != null) {
      switch (language.getLanguageCode()) {
        case LanguageCode.GERMAN:
          dtoLanguage = "German";
          break;
        case LanguageCode.ENGLISH:
          dtoLanguage = "English";
          break;
      }
    }

    return dtoLanguage;
  }

  public static Category mapCategory(de.fhb.entities.Category category) {
    Category dtoCategory = new Category();

    dtoCategory.setId(category.getId());
    dtoCategory.setName(category.getName());
    dtoCategory.setColor(category.getColor());

    HashMap<String, String> availableLanguagesMap = new HashMap<>();
    List<String> availableLanguages = new ArrayList<>();
    int numberOfVideos = 0;

    if (category.getPlaylists().size() > 0) {
      for (de.fhb.entities.Playlist playlist : category.getPlaylists()) {
        numberOfVideos += playlist.getVideos().size();
        availableLanguagesMap.put(
                mapLanguage(playlist.getLanguageCode()),
                mapLanguage(playlist.getLanguageCode())
        );
      }
    }

    dtoCategory.setNumberOfVideos(numberOfVideos);

    for (String key : availableLanguagesMap.keySet()) {
      availableLanguages.add(key);
    }

    dtoCategory.setAvailableLanguages(availableLanguages);

//    HashMap<String, Language> availableLanguagesMap = new HashMap<String, Language>();
    return dtoCategory;
  }
}
