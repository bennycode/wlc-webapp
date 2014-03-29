package de.fhb.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
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
  public void testJacksonMapping() throws IOException, URISyntaxException {
    Author instance = new Author("Benny Neugebauer");
    instance.setId(1L);

    String actual = mapper.writeValueAsString(instance);
    String expected = properties.getProperty("testJacksonMapping");

    assertEquals(expected, actual);
  }
}
