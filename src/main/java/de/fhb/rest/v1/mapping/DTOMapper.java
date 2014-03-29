package de.fhb.rest.v1.mapping;

import de.fhb.entities.Author;
import de.fhb.rest.v1.dto.Playlist;
import de.fhb.rest.v1.dto.Owner;
import de.fhb.rest.v1.dto.Status;

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

  public static Owner mapAuthor(Author author) {
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
    dtoPlaylist.setLanguage(playlist.getLanguageCode().getLanguageCode());
    dtoPlaylist.setCategoryName(playlist.getCategory().getName());
    dtoPlaylist.setProviderName(playlist.getProviderName().getProviderName());
    dtoPlaylist.setNumberOfVideos(playlist.getVideos().size());
    dtoPlaylist.setDescription(playlist.getDescription());
    dtoPlaylist.setOwner(mapAuthor(playlist.getAuthor()));
    dtoPlaylist.setStatus(new Status());

    return dtoPlaylist;
  }
}
