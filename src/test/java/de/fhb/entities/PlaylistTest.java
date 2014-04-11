package de.fhb.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fhb.entities.Language.LanguageCode;
import de.fhb.rest.v1.mapping.DTOMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlaylistTest {

  private static ObjectMapper mapper;
  private static Properties properties;

  public PlaylistTest() {
  }

  @BeforeClass
  public static void setUpClass() throws IOException {
    // Test cases
    String path = "PlaylistTest.properties";
    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    properties = new Properties();
    properties.load(in);

    // Jackson mapper
    mapper = new ObjectMapper();
  }

  @Test
  public void testRestServiceV1Mapping() throws IOException, URISyntaxException {
    Author author = new Author();
    author.setName("Tom Wendel & Felix Rieseberg");

    Category category = new Category();
    category.setName("Windows Phone");

    List<Video> videos = new ArrayList<>();
    videos.add(new Video());

    Playlist playlist = new Playlist();
    playlist.setId(1L);
    playlist.setName("Windows Phone Workshop (MMT30)");
    playlist.setLanguageCode(new Language(LanguageCode.GERMAN));
    playlist.setCategory(category);
    playlist.setProvider(Provider.YOUTUBE);
    playlist.setAuthor(author);
    playlist.setVideos(videos);

    de.fhb.rest.v1.dto.PlaylistDTO dtoPlaylist = DTOMapper.mapPlaylist(playlist);

    String actual = mapper.writeValueAsString(dtoPlaylist);
    String expected = properties.getProperty("bert");

    assertEquals(expected, actual);
  }
}
