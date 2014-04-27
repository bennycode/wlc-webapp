package com.welovecoding.rest.v2;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

/**
 *
 * @author Michael Koppen
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {

  private final ObjectMapper mapper;

  public ObjectMapperResolver() {
    mapper = new ObjectMapper();
    mapper.enable(Feature.INDENT_OUTPUT);
    mapper.enable(Feature.WRITE_EMPTY_JSON_ARRAYS);
  }

  @Override
  public ObjectMapper getContext(Class<?> type) {
    return mapper;
  }
}
