package de.fhb.config;

public class Pages {

  public static final String INDEX = "/index.xhtml";
  public static final String LOGIN = "/login.xhtml";
  public static final String ADMIN_LOGIN = "/login?faces-redirect=true";
  public static final String ADMIN_POST_LOGIN = "/admin/index?faces-redirect=true";
  public static final String ADMIN_AUTHORS = "/admin/authors";
  public static final String ADMIN_CATEGORY = "/admin/categories";
  public static final String ADMIN_INDEX = "/admin/index";
  public static final String ADMIN_PLAYLISTS = "/admin/playlists";
  public static final String ADMIN_VIDEOS = "/admin/videos";
  public static final String REST_VERSION_1 = "/rest/fhb/v1";
  public static final String REST_VERSION_2 = "/rest/fhb/v2";

  //REST V2
  public static final String REST_CATEGORY = REST_VERSION_2 + "/category/{0}";
  public static final String REST_PLAYLIST = REST_VERSION_2 + "/category/{0}/playlist/{1}";
  public static final String REST_VIDEO = REST_VERSION_2 + "/category/{0}/playlist/{1}/video/{2}";

}
