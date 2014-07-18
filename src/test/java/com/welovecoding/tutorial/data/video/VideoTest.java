package com.welovecoding.tutorial.data.video;

import com.welovecoding.tutorial.data.video.Video;
import com.welovecoding.tutorial.data.category.Category;
import com.welovecoding.tutorial.data.playlist.entity.Provider;
import com.welovecoding.tutorial.data.playlist.entity.LanguageCode;
import com.welovecoding.tutorial.data.playlist.entity.Playlist;
import com.welovecoding.tutorial.api.v1.dto.VideoDTO;
import com.welovecoding.tutorial.api.v1.mapping.DTOMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import org.codehaus.jackson.map.ObjectMapper;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class VideoTest {

  private static ObjectMapper mapper;
  private static Properties properties;

  public VideoTest() {
  }

  @BeforeClass
  public static void setUpClass() throws IOException {
    // Test cases
    String path = "VideoTest.properties";
    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    properties = new Properties();
    properties.load(in);

    // Jackson mapper
    mapper = new ObjectMapper();
  }

  @Test
  public void testRestServiceV1Mapping() throws IOException, URISyntaxException {
    Category category = new Category();
    category.setId(3L);
    category.setColor("#643EBF");
    category.setName("PHP");
    category.setSlug("php");

    Playlist playlist = new Playlist();
    playlist.setCategory(category);
    playlist.setId(8L);
    playlist.setCode("2CC78DADA62AFEE3");
    playlist.setLanguageCode(LanguageCode.de);
    playlist.setProvider(Provider.YOUTUBE);
    playlist.setName("Zend Framework Tutorial für Anfänger");
    playlist.setSlug("zend-framework-tutorial-fuer-anfaenger");

    Video video = new Video();
    video.setId(245L);
    video.setName("XAMPP - Virtual Host einrichten (1)");
    video.setDescription(null);
    video.setCode("N3NPTmkOxWU");
    video.setPlaylist(playlist);
    video.setPreviewImageUrl("http://img.youtube.com/vi/N3NPTmkOxWU/1.jpg");
    video.setDownloadUrl(null);
    video.setPermalink("http://www.welovecoding.com/tutorials/php/zend-framework-tutorial-fuer-anfaenger?video=0");

    VideoDTO dtoVideo = DTOMapper.mapVideo(video);

    String actual = mapper.writeValueAsString(dtoVideo);
    String expected = properties.getProperty("lauri");

    assertEquals(expected, actual);
  }
}
