package de.fhb.entities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import org.codehaus.jackson.map.ObjectMapper;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthorTest {

  private static ObjectMapper mapper;
  private static Properties properties;

  public AuthorTest() {
  }

  @BeforeClass
  public static void setUpClass() throws IOException {
    // Test cases
    String path = "AuthorTest.properties";
    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    properties = new Properties();
    properties.load(in);

    // Jackson mapper
    mapper = new ObjectMapper();
  }

  @Test
  /**
   * @see
   * http://stackoverflow.com/questions/3891375/how-to-read-a-text-file-resource-into-java-unit-test
   */
  public void testJacksonMapping() throws IOException, URISyntaxException {
    Author instance = new Author("Benny Neugebauer");
    instance.setId(1L);

    String actual = mapper.writeValueAsString(instance);
    String expected = properties.getProperty("testJacksonMapping");

    assertEquals(expected, actual);
  }
}
