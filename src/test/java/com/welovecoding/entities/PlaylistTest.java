package comwelovecodingb.entities;

import com.welovecoding.entities.Difficulty;
import com.welovecoding.entities.Video;
import com.welovecoding.entities.Category;
import com.welovecoding.entities.Playlist;
import com.welovecoding.entities.Author;
import com.welovecoding.entities.Provider;
import com.welovecoding.entities.LanguageCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.welovecoding.rest.v1.mapping.DTOMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static org.junit.Assert.assertEquals;
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
  public void testRestServiceV1Mapping() throws JsonProcessingException {
    Author author = new Author();
    author.setName("Tom Wendel & Felix Rieseberg");

    Category category = new Category();
    category.setName("Windows Phone");

    List<Video> videos = new ArrayList<>();
    videos.add(new Video());

    Playlist playlist = new Playlist();
    playlist.setId(1L);
    playlist.setName("Windows Phone Workshop (MMT30)");
    playlist.setLanguageCode(LanguageCode.DE);
    playlist.setCategory(category);
    playlist.setProvider(Provider.YOUTUBE);
    playlist.setAuthor(author);
    playlist.setVideos(videos);

    com.welovecoding.rest.v1.dto.PlaylistDTO dtoPlaylist = DTOMapper.mapPlaylist(playlist);

    String actual = mapper.writeValueAsString(dtoPlaylist);
    String expected = properties.getProperty("bert");

    System.out.println(actual);
    System.out.println(expected);

    assertEquals(expected, actual);
  }

  @Test
  public void testDifficultyV1Mapping() throws JsonProcessingException {
    Author author = new Author();
    author.setName("Benny Neugebauer");
    author.setWebsite("http://www.bennyn.de/");

    Category category = new Category();
    category.setName("Theorie");

    List<Video> videos = new ArrayList<>();
    videos.add(new Video());
    videos.add(new Video());
    videos.add(new Video());
    videos.add(new Video());
    videos.add(new Video());
    videos.add(new Video());
    videos.add(new Video());
    videos.add(new Video());

    Playlist playlist = new Playlist();
    playlist.setId(9L);
    playlist.setName("Formale Sprachen und Automatentheorie");
    playlist.setLanguageCode(LanguageCode.DE);
    playlist.setCategory(category);
    playlist.setProvider(Provider.YOUTUBE);
    playlist.setAuthor(author);
    playlist.setVideos(videos);
    playlist.setDifficulty(Difficulty.MEDIUM);

    com.welovecoding.rest.v1.dto.PlaylistDTO dtoPlaylist = DTOMapper.mapPlaylist(playlist);

    String actual = mapper.writeValueAsString(dtoPlaylist);
    String expected = properties.getProperty("berta");

    System.out.println(actual);
    System.out.println(expected);

    assertEquals(expected, actual);
  }
}
