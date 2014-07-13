package util;

import org.junit.After;
import org.junit.Before;

/**
 *
 * @author Michael Koppen
 */
public class IntegrationTest {

  protected static final String HOST = "http://localhost";
  protected static final String PORT = "9090";
  protected static final String ROOT = HOST + ":" + PORT + "/" + GFInstance.APP_NAME;

  @Before
  public void setUp() throws Exception {
    GFInstance.deployer.deploy(GFInstance.archive.toURI(), "--contextroot=" + GFInstance.APP_NAME, "--force=true");
  }

  @After
  public void tearDown() throws Exception {
    GFInstance.deployer.undeploy(GFInstance.APP_NAME);
  }
}
