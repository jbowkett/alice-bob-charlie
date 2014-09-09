package info.bowkett.abc.shell;

import info.bowkett.abc.commands.*;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * CommandParser Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Aug 29, 2014</pre>
 */
public class CommandFactoryTest {

  private CommandFactory commandFactory;

  @Before
  public void before() throws Exception {
    commandFactory = new CommandFactory();
  }

  @Test
  public void testSubmitValidPostCommand() throws Exception {
    final Command com = commandFactory.submit("Alice -> I love the weather today");
    assertTrue(com instanceof PostCommand);
  }

  @Test
  public void testSubmitUserCommandYieldsAViewCommand() throws Exception {
    final Command com = commandFactory.submit("Alice");
    assertTrue(com instanceof ViewCommand);
  }

  @Test
  public void testFollowUserYieldsAFollowUserCommand() throws Exception {
    final Command com = commandFactory.submit("Alice follows Bob");
    assertTrue(com instanceof FollowCommand);
  }

  @Test
  public void testFollowUserCommandGetsPersonBeingFollowed() throws Exception {
    final FollowCommand com = (FollowCommand) commandFactory.submit("Alice follows Bob");
    assertEquals("Bob", com.getUserNameBeingFollowed());
  }

  @Test
  public void testFollowUserCommandGetsPersonDoingFollowed() throws Exception {
    final Command com = commandFactory.submit("Alice follows Bob");
    assertEquals("Alice", com.getUserName());
  }

  @Test
  public void testWallYieldsWallCommand() throws Exception {
    final Command com = commandFactory.submit("Bob wall");
    assertTrue(com instanceof WallCommand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandYieldsException() {
    commandFactory.submit("Bob wall-e");
  }

  @Test
  public void testSubmitValidPostCommandContainsUserName() throws Exception {
    final Command com = commandFactory.submit("Alice -> I love the weather today");
    assertEquals("Alice", com.getUserName());
  }

  @Test
  public void testSubmitValidPostCommandContainsPostText() throws Exception {
    final PostCommand com = (PostCommand) commandFactory.submit("Alice -> I love the weather today");
    assertEquals("I love the weather today", com.getText());
  }
}
