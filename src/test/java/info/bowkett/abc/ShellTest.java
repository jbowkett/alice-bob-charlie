package info.bowkett.abc;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * Shell Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Aug 29, 2014</pre>
 */
public class ShellTest {

  private Shell shell;

  @Before
  public void before() throws Exception {
    shell = new Shell(new CommandParser(), new InMemoryUserRepository());
  }

  @After
  public void after() throws Exception {
  }
}
