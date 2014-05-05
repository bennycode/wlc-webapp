package com.welovecoding.rest.v2;

import static com.welovecoding.config.Pages.REST_VERSION_2;
import com.welovecoding.rest.v2.resource.CategoryResource2;
import com.welovecoding.rest.v2.resource.PlaylistResource2;
import com.welovecoding.rest.v2.resource.VideoResource2;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath(REST_VERSION_2)
public class RestConfig extends ResourceConfig {

  public static final String JSON_MEDIATYPE = "application/json;charset=utf-8";

  public RestConfig() {
    // registers REST-Resources and Jackson JSON mapping
    register(CategoryResource2.class);
    register(PlaylistResource2.class);
    register(VideoResource2.class);
    register(ObjectMapperResolver.class);
    register(JacksonFeature.class);
//    packages(Packages.REST_RESOURCES_V2, "com.fasterxml.jackson.jaxrs.base");
    // Disbables MOXy JSON mapping
    property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
    // Registers LoggingFilter for Request and Responses
    register(LoggingFilter.class);
  }

}
