package com.welovecoding.rest.v1;

import com.welovecoding.config.Packages;
import static com.welovecoding.config.Pages.REST_VERSION_1;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath(REST_VERSION_1)
public class RestConfig extends ResourceConfig {

  public static final String JSON_MEDIATYPE = "application/json;charset=utf-8";

  public RestConfig() {
    // registers REST-Resources and Jackson JSON mapping
    packages(Packages.REST_RESOURCES_V1, "com.fasterxml.jackson.jaxrs.base");
    // Disbables MOXy JSON mapping
    property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
    // Registers LoggingFilter for Request and Responses
    register(LoggingFilter.class);
  }

}
