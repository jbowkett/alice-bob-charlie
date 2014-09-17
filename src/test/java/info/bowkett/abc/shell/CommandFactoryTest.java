package info.bowkett.abc.shell;

import info.bowkett.abc.commands.*;
import info.bowkett.abc.dal.*;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

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
    commandFactory = new CommandFactory(mock(DataRepository.class));
  }

  @Test
  public void testSubmitValidPostCommand() throws Exception {
    final Command com = commandFactory.getCommand("Alice -> I love the weather today");
    assertTrue(com instanceof PostCommand);
  }

  @Test
  public void testSubmitUserCommandYieldsAViewCommand() throws Exception {
    final Command com = commandFactory.getCommand("Alice");
    assertTrue(com instanceof ReadCommand);
  }

  @Test
  public void testWallYieldsWallCommand() throws Exception {
    final Command com = commandFactory.getCommand("Bob wall");
    assertTrue(com instanceof WallCommand);
  }

  @Test
  public void testFollowUserYieldsAFollowUserCommand() throws Exception {
    final Command com = commandFactory.getCommand("Alice follows Bob");
    assertTrue(com instanceof FollowCommand);
  }
  @Test
  public void testQuitCommand() {
    final Command command = commandFactory.getCommand("quit");
    assertTrue(command instanceof QuitCommand);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandYieldsException() {
    commandFactory.getCommand("Bob wall-e");
  }


  @Test
  public void testFollowUserCommandGetsPersonBeingFollowed() throws Exception {
    final FollowCommand com = (FollowCommand) commandFactory.getCommand("Alice follows Bob");
    assertEquals("Bob", com.getUserNameBeingFollowed());
  }

  @Test
  public void testFollowUserCommandGetsPersonDoingFollowing() throws Exception {
    final Command com = commandFactory.getCommand("Alice follows Bob");
    assertEquals("Alice", ((FollowCommand) com).getUserName());
  }

  @Test
  public void testSubmitValidPostCommandContainsUserName() throws Exception {
    final Command com = commandFactory.getCommand("Alice -> I love the weather today");
    assertEquals("Alice", ((PostCommand)com).getUserName());
  }

  @Test
  public void testSubmitValidPostCommandContainsPostText() throws Exception {
    final PostCommand com = (PostCommand) commandFactory.getCommand("Alice -> I love the weather today");
    assertEquals("I love the weather today", com.getText());
  }
}
