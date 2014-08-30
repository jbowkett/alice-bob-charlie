package info.bowkett.abc;

import info.bowkett.abc.commands.Command;
import info.bowkett.abc.commands.PostCommand;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Shell Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Aug 29, 2014</pre>
 */
public class CommandParserTest {

  private CommandParser commandParser;

  @Before
  public void before() throws Exception {
    commandParser = new CommandParser();
  }

  @After
  public void after() throws Exception {
  }

  /**
   * Method: submit(String shellCommand)
   */
  @Test
  public void testSubmitValidPostCommand() throws Exception {
    Command com = commandParser.submit("Alice -> I love the weather today");
    assertTrue (com instanceof PostCommand);
  }

  @Test
  public void testSubmitValidPostCommandContainsUserName() throws Exception {
    Command com = commandParser.submit("Alice -> I love the weather today");
    assertEquals("Alice", com.getUserName());
  }
}
