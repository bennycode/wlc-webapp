package util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 *
 * @author Michael Koppen
 */
public class TestRunListener extends RunListener {

  private static final Logger LOG = Logger.getLogger(TestRunListener.class.getName());

  @Override
  public void testIgnored(Description description) throws Exception {
    LOG.log(Level.INFO, "testIgnored was called!");
  }

  @Override
  public void testAssumptionFailure(Failure failure) {
    LOG.log(Level.INFO, "testAssumptionFailure was called!");
  }

  @Override
  public void testFailure(Failure failure) throws Exception {
    LOG.log(Level.INFO, "testFailure was called!");
  }

  @Override
  public void testFinished(Description description) throws Exception {
//    LOG.log(Level.INFO, "testFinished was called!");
  }

  @Override
  public void testStarted(Description description) throws Exception {
//    LOG.log(Level.INFO, "testStarted was called!");
  }

  @Override
  public void testRunFinished(Result result) throws Exception {
//    LOG.log(Level.INFO, "testRunFinished was called!");
  }

  @Override
  public void testRunStarted(Description description) throws Exception {
//    LOG.log(Level.INFO, "testRunStarted was called!");
  }

}
