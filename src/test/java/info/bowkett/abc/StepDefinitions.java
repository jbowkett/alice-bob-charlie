package info.bowkett.abc;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.InOrder;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


/**
 * Created by jbowkett on 28/08/2014.
 */
public class StepDefinitions {

  private final CommandParser commandParser;
  private final Shell shell;
  private final TimelineRepository timelineRepo;
  private final Console console;
  private final Console consoleSpy;
  private final FollowRepository followRepo;
  private UserRepository userRepo;

  public StepDefinitions() {
    commandParser = new CommandParser();
    this.userRepo = new InMemoryUserRepository();
    timelineRepo = new InMemoryTimelineRepository();
    console = new Console(new Timeformat());
    consoleSpy = spy(console);
    followRepo = new InMemoryFollowRepository();
    shell = new Shell(commandParser, userRepo, timelineRepo, consoleSpy, followRepo);
  }

  @When("^\"(.*?)\" posts \"(.*?)\"$")
  public void posts(String userName, String message) throws Throwable {
    final String post = post(userName, message);
    shell.submit(post);
  }

  @Then("^\"(.*?)\" timeline contains the post \"(.*?)\"$")
  public void timeline_contains_the_post(String posessive, String post) throws Throwable {
    final String userName = stripPosessive(posessive);
    final User user = userRepo.get(userName);
    final Timeline posts = timelineRepo.get(user);
    assertTrue(posts.stream().anyMatch(p -> p.getText().equals(post)));
  }

  @Then("^\"(.*?)\" timeline contains (\\d+) posts$")
  public void timeline_contains_posts(String posessive, int expectedCount) throws Throwable {
    final String userName = stripPosessive(posessive);
    final User user = userRepo.get(userName);
    final Timeline timeline = timelineRepo.get(user);
    assertEquals(expectedCount, timeline.size());
  }

  @When("^reading the posts by \"(.*?)\"$")
  public void reading_the_posts_by(String userName) throws Throwable {
    shell.submit(userName);
  }

  @Then("^I see \"(.*?)\"$")
  public void i_see(String post) throws Throwable {
    verify(consoleSpy).print(post);
  }

  @When("^\"(.*?)\" follows \"(.*?)\"$")
  public void follows(String userNameDoingFollowing, String userNameBeingFollowed) throws Throwable {
    final String follow = follow(userNameDoingFollowing, userNameBeingFollowed);
    shell.submit(follow);
    final User userDoingFollowing = userRepo.get(userNameDoingFollowing);
    final Set<User> usersBeingFollowed = followRepo.getSubscriptionsFor(userDoingFollowing);
    assertTrue(usersBeingFollowed.stream().anyMatch(u -> u.getName().equals(userNameBeingFollowed)));
  }

  @When("^\"(.*?)\" views their wall$")
  public void views_their_wall(String userName) throws Throwable {
    shell.submit(userName + " wall");
  }

  @Then("^I see the wall contains:$")
  public void i_see_the_wall(String wallText) throws Throwable {
    final String[] lines = wallText.split("\n");
    final InOrder inOrder = inOrder(consoleSpy);
    for (String line : lines) {
      inOrder.verify(consoleSpy).print(line);
      inOrder.verify(consoleSpy).timestamp(anyLong());
    }
  }

  String stripPosessive(String posessive) {
    return posessive.replaceAll("'s$", "");
  }

  private String follow(String userDoingFollowing, String userBeingFollowed) {
    return userDoingFollowing + " follows " + userBeingFollowed;
  }

  private String post(String userName, String message) {
    return userName + " -> " + message;
  }
}
