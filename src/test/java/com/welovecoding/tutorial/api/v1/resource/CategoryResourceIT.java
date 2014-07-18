package com.welovecoding.tutorial.api.v1.resource;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV3;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static com.jayway.restassured.module.jsv.JsonSchemaValidatorSettings.settings;
import com.jayway.restassured.response.Response;
import java.io.File;
import java.io.InputStream;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import util.IntegrationTest;

/**
 *
 * @author Michael Koppen
 */
public class CategoryResourceIT extends IntegrationTest {

  @Rule
  // Get name of actual Test with test.getMethodName()
  public TestName test = new TestName();

  private static final String CATEGORY_LIST_SCHEMA = "json-schema/categoryList-schema.json";

  @BeforeClass
  public static void setUpClass() throws Exception {

    flatXmlDataSet = new FlatXmlDataSet(new File("src/test/resources", "full.xml"), false, true);
    JsonSchemaValidator.settings = settings().with().jsonSchemaFactory(
            JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV3).freeze()).freeze()).
            and().with().checkedValidation(false);
  }

  @AfterClass
  public static void tearDownClass() throws Exception {

  }

  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();
  }

  @After
  @Override
  public void tearDown() throws Exception {
    super.tearDown();
  }

  private static InputStream getSchema(String schemaPath) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaPath);
  }

  /**
   * Test of getCategories method, of class CategoryResource.
   */
  @Test
  public void testGetCategories() throws Exception {
    System.out.println(test.getMethodName());
    InputStream schema = getSchema(CATEGORY_LIST_SCHEMA);

    Response resp
            = given().
            when().
            get(ROOT + "/rest/service/v1/categories").then().
            extract().response();
    System.out.println("RESPONSE: ");
    resp.prettyPrint();
    resp.then().assertThat().body(matchesJsonSchema(schema));
  }

}
