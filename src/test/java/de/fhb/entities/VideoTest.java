package de.fhb.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fhb.rest.v1.mapping.DTOMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import static org.junit.Assert.*;
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
    Video video = new Video();
    video.setId(245L);
    video.setName("XAMPP - Virtual Host einrichten (1)");
    video.setDescription(null);
    video.setCode("N3NPTmkOxWU");
    video.setPreviewImageUrl("http://img.youtube.com/vi/N3NPTmkOxWU/1.jpg");
    video.setDownloadUrl(null);
    video.setPermalink("http://www.welovecoding.com/tutorials/php/zend-framework-tutorial-fuer-anfaenger?video=0");

    de.fhb.rest.v1.dto.Video dtoVideo = DTOMapper.mapVideo(video);

    String actual = mapper.writeValueAsString(dtoVideo);
    String expected = properties.getProperty("lauri");

    assertEquals(expected, actual);
  }
}
