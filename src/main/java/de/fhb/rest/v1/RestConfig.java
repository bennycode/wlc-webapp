package de.fhb.rest.v1;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest/fhb/v1")
public class RestConfig extends ResourceConfig {

  public RestConfig() {
    // registers REST-Resources and Jackson JSON mapping
    packages("de.fhb.rest.v1.resource", "com.fasterxml.jackson.jaxrs.base");
    // Disbables MOXy JSON mapping
    property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
    // Registers LoggingFilter for Request and Responses
    register(LoggingFilter.class);
  }

}
