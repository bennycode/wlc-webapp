package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
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
  protected static Connection JDBC_CONNECTION;
  protected static IDatabaseConnection DATABASE_CONNECTION;
  protected static FlatXmlDataSet flatXmlDataSet;

  public IntegrationTest() {

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
