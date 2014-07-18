package com.welovecoding.tutorial.data.category;

import com.welovecoding.tutorial.data.video.Video;
import com.welovecoding.tutorial.data.category.Category;
import com.welovecoding.tutorial.data.playlist.entity.LanguageCode;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.api.v1.mapping.DTOMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.codehaus.jackson.map.ObjectMapper;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class CategoryTest {

  private static ObjectMapper mapper;
  private static Properties properties;

  public CategoryTest() {
  }

  @BeforeClass
  public static void setUpClass() throws IOException {
    // Test cases
    String path = "CategoryTest.properties";
    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    properties = new Properties();
    properties.load(in);

    // Jackson mapper
    mapper = new ObjectMapper();
  }

  @Test
  public void testRestServiceV1Mapping() throws IOException, URISyntaxException {
    // Android
    Category android = new Category();
    android.setId(8L);
    android.setName("Android");
    android.setColor("#8CBE29");

    List<Video> androidVideos = new ArrayList<>();
    androidVideos.add(new Video());

    Playlist androidPlaylistEnglish = new Playlist();
    androidPlaylistEnglish.setCategory(android);
    androidPlaylistEnglish.setLanguageCode(LanguageCode.en);
    androidPlaylistEnglish.setVideos(androidVideos);

    Playlist androidPlaylistGerman = new Playlist();
    androidPlaylistGerman.setCategory(android);
    androidPlaylistGerman.setLanguageCode(LanguageCode.de);
    androidPlaylistGerman.setVideos(androidVideos);

    List<Playlist> androidPlaylists = new ArrayList<>();
    androidPlaylists.add(androidPlaylistEnglish);
    androidPlaylists.add(androidPlaylistGerman);

    android.setPlaylists(androidPlaylists);

    // C
    Category c = new Category();
    c.setId(7L);
    c.setName("C");
    c.setColor("#EF9608");

    List<Video> cVideos = new ArrayList<>();
    cVideos.add(new Video());

    Playlist cPlaylistGerman = new Playlist();
    cPlaylistGerman.setCategory(c);
    cPlaylistGerman.setLanguageCode(LanguageCode.de);
    cPlaylistGerman.setVideos(cVideos);

    List<Playlist> cPlaylists = new ArrayList<>();
    cPlaylists.add(cPlaylistGerman);

    c.setPlaylists(cPlaylists);

    com.welovecoding.tutorial.api.v1.dto.CategoryDTO dtoAndroid = DTOMapper.mapCategory(android);
    com.welovecoding.tutorial.api.v1.dto.CategoryDTO dtoC = DTOMapper.mapCategory(c);

    List<com.welovecoding.tutorial.api.v1.dto.CategoryDTO> dtoCategories = new ArrayList<>();
    dtoCategories.add(dtoAndroid);
    dtoCategories.add(dtoC);

    String actual = mapper.writeValueAsString(dtoCategories);
    String expected = properties.getProperty("cola");

    assertEquals(expected, actual);
  }
}
