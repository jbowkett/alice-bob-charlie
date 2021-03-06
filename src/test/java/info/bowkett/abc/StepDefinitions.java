package info.bowkett.abc;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import info.bowkett.abc.datastore.*;
import info.bowkett.abc.domain.Post;
import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.console.*;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * Created by jbowkett on 28/08/2014.
 */
public class StepDefinitions {

  private final TimelineDAO timelineRepo;
  private final Console consoleSpy;
  private final FollowDAO followRepo;
  private UserDAO userRepo;

  public StepDefinitions() {
    this.userRepo = new UserDAOInMemory();
    timelineRepo = new TimelineDAOInMemory();
    followRepo = new FollowDAOInMemory();
    final DataRepository dataRepo = new DataRepositoryImpl(userRepo, timelineRepo, followRepo);
    final CommandFactory commandFactory = new CommandFactory(dataRepo);
    final Console console = new Console(new Timeformat(), commandFactory);
    consoleSpy = spy(console);
  }

  @When("^\"(.*?)\" posts \"(.*?)\"$")
  public void posts(String userName, String message) throws Throwable {
    Thread.sleep(1000);
    final String post = post(userName, message);
    consoleSpy.submit(post);
  }

  @Then("^\"(.*?)\" timeline contains the post \"(.*?)\"$")
  public void timeline_contains_the_post(String posessive, String post) throws Throwable {
    final String userName = stripPosessive(posessive);
    final User user = userRepo.read(userName);
    final Timeline posts = timelineRepo.read(user);
    final List<Post> extractedPosts = new ArrayList<>();
    posts.forEachRecentFirst(extractedPosts::add);
    assertTrue(extractedPosts.stream().anyMatch(p -> p.getText().equals(post)));
  }

  @Then("^\"(.*?)\" timeline contains (\\d+) posts$")
  public void timeline_contains_posts(String posessive, int expectedCount) throws Throwable {
    final String userName = stripPosessive(posessive);
    final User user = userRepo.read(userName);
    final Timeline timeline = timelineRepo.read(user);
    final List<Post> extractedPosts = new ArrayList<>();
    timeline.forEachRecentFirst(extractedPosts::add);
    assertEquals(expectedCount, extractedPosts.size());
  }

  @When("^reading the posts by \"(.*?)\"$")
  public void reading_the_posts_by(String userName) throws Throwable {
    consoleSpy.submit(userName);
  }

  @Then("^I see:$")
  public void i_see(String posts) throws Throwable {
    final String[] lines = posts.split("\n");
    final InOrder inOrder = inOrder(consoleSpy);
    for (String line : lines) {
      inOrder.verify(consoleSpy).print(line);
      inOrder.verify(consoleSpy).timestamp(anyLong());
    }
  }

  @When("^\"(.*?)\" follows \"(.*?)\"$")
  public void follows(String userNameDoingFollowing, String userNameBeingFollowed) throws Throwable {
    final String follow = follow(userNameDoingFollowing, userNameBeingFollowed);
    consoleSpy.submit(follow);
    final User userDoingFollowing = userRepo.read(userNameDoingFollowing);
    final Set<User> usersBeingFollowed = followRepo.getUsersFollowedBy(userDoingFollowing);
    assertTrue(usersBeingFollowed.stream().anyMatch(u -> u.getName().equals(userNameBeingFollowed)));
  }

  @When("^\"(.*?)\" views their wall$")
  public void views_their_wall(String userName) throws Throwable {
    consoleSpy.submit(userName + " wall");
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
