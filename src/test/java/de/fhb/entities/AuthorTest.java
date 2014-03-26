package de.fhb.entities;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthorTest {

  private static ObjectMapper mapper;

  public AuthorTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    mapper = new ObjectMapper();
  }

  @Test
  public void testJacksonMapping() throws IOException {
    Author instance = new Author("Benny Neugebauer");
    instance.setId(1L);

    String expected = "{\"id\":1,\"name\":\"Benny Neugebauer\",\"description\":\"\",\"website\":\"\",\"channelUrl\":\"\"}";
    String actual = mapper.writeValueAsString(instance);

    assertEquals(expected, actual);
  }
}
