package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Michael Koppen
 */
public class IntegrationTest {

  private static final Logger LOG = Logger.getLogger(IntegrationTest.class.getName());

  protected static final String HOST = "http://localhost";
  protected static final String PORT = "9090";
  protected static final String ROOT = HOST + ":" + PORT + "/" + GFInstance.APP_NAME;
  protected static Connection JDBC_CONNECTION;
  protected static IDatabaseConnection DATABASE_CONNECTION;
  protected static FlatXmlDataSet flatXmlDataSet;
  protected static boolean singleIntegrationTestRunDetected = false;
  protected static InitialContext context;

  public static <T> T lookupBy(Class<T> type) throws NamingException {
    return (T) context.lookup("java:global/" + GFInstance.APP_NAME + "/"
            + type.getSimpleName());
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
    LOG.info("[IntegrationTest] calling setUpClass()");
    if (GFInstance.glassfish == null) {
      singleIntegrationTestRunDetected = true;
      GFInstance.startInstance();
    }
    context = new InitialContext();
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
    LOG.info("[IntegrationTest] calling tearDownClass()");
    if (singleIntegrationTestRunDetected) {
      GFInstance.stopInstance();
    }
  }

  @Before
  public void setUp() throws Exception {
    GFInstance.deployer.deploy(GFInstance.archive.toURI(), "--contextroot=" + GFInstance.APP_NAME, "--force=true");
    try {
      JDBC_CONNECTION = DriverManager.getConnection("jdbc:hsqldb:mem:wlc", "sa", "");
      DATABASE_CONNECTION = new DatabaseConnection(JDBC_CONNECTION);
      DATABASE_CONNECTION.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
    } catch (SQLException | DatabaseUnitException ex) {
      Logger.getLogger(IntegrationTest.class.getName()).log(Level.SEVERE, null, ex);
      try {
        JDBC_CONNECTION.close();
      } catch (SQLException ex1) {
        Logger.getLogger(IntegrationTest.class.getName()).log(Level.SEVERE, null, ex1);
      }
    }
    DatabaseOperation.CLEAN_INSERT.execute(DATABASE_CONNECTION, flatXmlDataSet);
  }

  @After
  public void tearDown() throws Exception {
    JDBC_CONNECTION.close();
    GFInstance.deployer.undeploy(GFInstance.APP_NAME);
  }

}
