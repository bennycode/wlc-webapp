package de.fhb.rest.v2;

import de.fhb.config.Packages;
import static de.fhb.config.Pages.REST_VERSION_2;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath(REST_VERSION_2)
public class RestConfig extends ResourceConfig {

  public RestConfig() {
    // registers REST-Resources and Jackson JSON mapping
    packages(Packages.REST_RESOURCES_V2, "com.fasterxml.jackson.jaxrs.base");
    // Disbables MOXy JSON mapping
    property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
    // Registers LoggingFilter for Request and Responses
    register(LoggingFilter.class);
  }

}
