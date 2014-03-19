/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.IOException;
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

  public static Deployer deployer;
  public static GlassFish glassfish;
  public static ScatteredArchive archive;
  public static final String APP_NAME = "WeLoveCodingTest";

  public static void startInstance() throws IOException {
    try {
      GlassFishProperties glassfishProperties = new GlassFishProperties();
      File configFile = new File("src/test/resources/META-INF", "domain.xml");
      glassfishProperties.setConfigFileURI(configFile.toURI().toString());
      glassfish = GlassFishRuntime.bootstrap().newGlassFish(glassfishProperties);
      glassfish.start();
      deployer = glassfish.getDeployer();
      archive = new ScatteredArchive(APP_NAME, ScatteredArchive.Type.WAR);
      archive.addClassPath(new File("target", "embedded-classes"));
    } catch (GlassFishException gfe) {
      System.out.println("GFE: " + gfe + "\n\n" + gfe.getMessage());
    }

  }

  public static void stopInstance() {
//    try {
//      glassfish.stop();
//      glassfish.dispose();
//    } catch (GlassFishException ex) {
//      Logger.getLogger(GFInstance.class.getName()).log(Level.SEVERE, null, ex);
//    }
  }

}
