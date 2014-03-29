package de.fhb.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
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
  public void testJacksonMapping() throws IOException, URISyntaxException {
    Playlist instance = new Playlist();
    instance.setId(1L);
    instance.setName("Windows Phone Workshop (MMT30)");
    instance.setLang(new Language("German"));
    
    String actual = mapper.writeValueAsString(instance);
    String expected = properties.getProperty("testPlaylistMapping");
    
    assertEquals(expected, actual);
  }
}
