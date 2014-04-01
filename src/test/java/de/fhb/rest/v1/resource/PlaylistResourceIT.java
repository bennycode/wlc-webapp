/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.rest.v1.resource;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV3;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static com.jayway.restassured.module.jsv.JsonSchemaValidatorSettings.settings;
import com.jayway.restassured.response.Response;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import util.GFInstance;

/**
 *
 * @author MacYser
 */
public class PlaylistResourceIT {

  @Rule
  // Get name of actual Test with test.getMethodName()
  public TestName test = new TestName();

  private static final String HOST = "http://localhost";
  private static final String PORT = "9090";
  private static final String APP_NAME = "WeLoveCodingTest";
  private static final String ROOT = HOST + ":" + PORT + "/" + APP_NAME;

  private static final String PLAYLIST_LIST_SCHEMA = "json-schema/playlistList-schema.json";

  @BeforeClass
  public static void setUpClass() throws Exception {

    JsonSchemaValidator.settings = settings().with().jsonSchemaFactory(
            JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV3).freeze()).freeze()).
            and().with().checkedValidation(false);
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    GFInstance.deployer.deploy(GFInstance.archive.toURI(), "--contextroot=" + GFInstance.APP_NAME, "--force=true");
  }

  @After
  public void tearDown() throws Exception {
    GFInstance.deployer.undeploy(GFInstance.APP_NAME);
  }

  private static InputStream getSchema(String schemaPath) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaPath);
  }

  /**
   * Test of getPlaylists method, of class PlaylistResource.
   */
  @Test
  public void testGetPlaylists() throws Exception {
    System.out.println("getPlaylists");
    InputStream schema = getSchema(PLAYLIST_LIST_SCHEMA);

    Response resp
            = given().
            pathParam("id", 1).
            when().
            get(ROOT + "/rest/fhb/v1/category/{id}").then().
            extract().response();
    System.out.println("RESPONSE: ");
    resp.prettyPrint();
    resp.then().assertThat().body(matchesJsonSchema(schema));
  }

}
