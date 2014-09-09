package info.bowkett.abc;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import info.bowkett.abc.dal.*;
import info.bowkett.abc.domain.Subscriptions;
import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.shell.*;
import org.mockito.InOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * Created by jbowkett on 28/08/2014.
 */
public class StepDefinitions {

  private final Shell shell;
  private final TimelineRepository timelineRepo;
  private final Console consoleSpy;
  private final FollowRepository followRepo;
  private UserRepository userRepo;

  public StepDefinitions() {
    this.userRepo = new InMemoryUserRepository();
    timelineRepo = new InMemoryTimelineRepository();
    followRepo = new InMemoryFollowRepository();
    final WallFactory wallFactory = new WallFactory(followRepo, timelineRepo);
    final CommandFactory commandFactory = new CommandFactory(userRepo, timelineRepo, followRepo, wallFactory);
    final Console console = new Console(new Timeformat());
    consoleSpy = spy(console);
    shell = new Shell(commandFactory, userRepo, timelineRepo, consoleSpy, followRepo, wallFactory);
  }

  @When("^\"(.*?)\" posts \"(.*?)\"$")
  public void posts(String userName, String message) throws Throwable {
    Thread.sleep(1000);
    final String post = post(userName, message);
    shell.submit(post);
  }

  @Then("^\"(.*?)\" timeline contains the post \"(.*?)\"$")
  public void timeline_contains_the_post(String posessive, String post) throws Throwable {
    final String userName = stripPosessive(posessive);
    final User user = userRepo.get(userName);
    final Timeline posts = timelineRepo.get(user);
    assertTrue(posts.anyMatch(p -> p.getText().equals(post)));
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

  @Then("^I see:$")
  public void i_see(String posts) throws Throwable {
    final String[] lines = posts.split("\n");
    final InOrder inOrder = inOrder(consoleSpy);
    for (String line : lines) {
      inOrder.verify(consoleSpy).print(line);
//      inOrder.verify(consoleSpy).timestamp(anyLong());
    }
  }

  @When("^\"(.*?)\" follows \"(.*?)\"$")
  public void follows(String userNameDoingFollowing, String userNameBeingFollowed) throws Throwable {
    final String follow = follow(userNameDoingFollowing, userNameBeingFollowed);
    shell.submit(follow);
    final User userDoingFollowing = userRepo.get(userNameDoingFollowing);
    final Subscriptions usersBeingFollowed = followRepo.getSubscriptionsFor(userDoingFollowing);
    assertTrue(usersBeingFollowed.stream().anyMatch(u -> u.getName().equals(userNameBeingFollowed)));
  }

  @When("^\"(.*?)\" views their wall$")
  public void views_their_wall(String userName) throws Throwable {
    shell.submit(userName + " wall");
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
