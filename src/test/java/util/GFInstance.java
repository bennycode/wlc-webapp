/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.embeddable.CommandResult;
import org.glassfish.embeddable.CommandRunner;
import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.glassfish.embeddable.archive.ScatteredArchive;

/**
 *
 * @author MacYser
 */
public class GFInstance {

  private static final Logger LOG = Logger.getLogger(GFInstance.class.getName());

  public static Deployer deployer;
  public static GlassFish glassfish;
  public static ScatteredArchive archive;
  public static final String APP_NAME = "WeLoveCodingTest";

  public static void startInstance() throws Exception {

    GlassFishProperties glassfishProperties = new GlassFishProperties();
    File configFile = new File("src/test/resources/META-INF", "domain.xml");
    glassfishProperties.setConfigFileURI(configFile.toURI().toString());

    glassfish = GlassFishRuntime.bootstrap().newGlassFish(glassfishProperties);

    // Set the log levels. For example, set 'deployment' and 'server' log levels to FINEST 
    Logger.getLogger("").getHandlers()[0].setLevel(Level.WARNING);
    Logger.getLogger("javax.enterprise.system.tools.deployment").setLevel(Level.WARNING);
    Logger.getLogger("javax.enterprise.system").setLevel(Level.WARNING);

    glassfish.start();

    deployer = glassfish.getDeployer();
    archive = new ScatteredArchive(APP_NAME, ScatteredArchive.Type.WAR);
    archive.addClassPath(new File("target", "classes"));

    archive.addMetadata(new File("target", "wlc-webapp/WEB-INF/glassfish-resources.xml"));

    for (File jar : new File("target", "wlc-webapp/WEB-INF/lib/").listFiles()) {
      archive.addClassPath(jar);
    }

  }

  public static void stopInstance() throws Exception {
    glassfish.dispose();
  }

  @Deprecated
  public static void createJDBCResourceJNDI(GlassFish glassfish) throws GlassFishException {
    CommandRunner commandRunner = glassfish.getCommandRunner();
    CommandResult result = commandRunner.run(
            "create-jdbc-resource",
            "--connectionpoolid=mysql_welovecoding_welovecodingPool",
            "--enabled=true",
            /*"--description description", */
            /*"--property (property=value):name=value*", */
            /*"--target target", */
            "java:app/jdbc/welovecoding");

    System.out.println("createJDBCResourceJNDI-Result: " + result.getOutput());
    System.out.println(result.getFailureCause());
  }

  @Deprecated
  public static void createJDBCConnectionPool(GlassFish glassfish) throws GlassFishException {
    CommandRunner commandRunner = glassfish.getCommandRunner();

    String jdbcUsername = System.getProperty("jdbcUsername", "welovecoding");
    String jdbcPassword = System.getProperty("jdbcPassword", "123456");
    String jdbcURL = System.getProperty("jdbcURL", "jdbc:mysql://localhost:3306/welovecoding");
    jdbcURL = jdbcURL.replaceAll(":", "\\\\:");

    CommandResult result = commandRunner.run(
            "create-jdbc-connection-pool",
            "--datasourceclassname=com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
            "--restype=javax.sql.DataSource",
            "--steadypoolsize=8",
            "--maxpoolsize=32",
            "--maxwait=60000",
            "--poolresize=2",
            "--idletimeout=170" /*
             ,"--initsql=initsqlstring"
             ,"--isolationlevel=isolationlevel"
             */, "--isisolationguaranteed=true",
            "--isconnectvalidatereq=false",
            "--validationmethod=auto-commit" /*
             ,"--validationtable=validationtable"
             */, "--failconnection=false",
            "--allownoncomponentcallers=false",
            "--nontransactionalconnections=false",
            "--validateatmostonceperiod=0",
            "--leaktimeout=0",
            "--leakreclaim=false",
            "--statementleaktimeout=0",
            "--statementleakreclaim=false",
            "--creationretryattempts=0",
            "--creationretryinterval=10" /*
             ,"--sqltracelisteners=sqltracelisteners,sqltracelisteners"
             */, "--statementtimeout=-1",
            "--lazyconnectionenlistment=false",
            "--lazyconnectionassociation=false",
            "--associatewiththread=false" /*
             ,"--driverclassname=jdbcdriverclassname"
             */, "--matchconnections=false",
            "--maxconnectionusagecount=0",
            "--ping=false",
            "--pooling=true",
            "--statementcachesize=0" /*
             ,"--validationclassname=validationclassname"
             */, "--wrapjdbcobjects=false" /*
             ,"--description description"
             */, "--property=user=" + jdbcUsername + ":password=" + jdbcPassword + ":url=" + jdbcURL /*
             ,"--target=target"
             */, "mysql_welovecoding_welovecodingPool");

    System.out.println("createJDBCConnectionPool-Result: " + result.getOutput());
    System.out.println(result.getFailureCause());
  }

}
