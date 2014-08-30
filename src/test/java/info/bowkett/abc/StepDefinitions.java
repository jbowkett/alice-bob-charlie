package info.bowkett.abc;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import info.bowkett.abc.commands.PostCommand;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


/**
 * Created by jbowkett on 28/08/2014.
 */
public class StepDefinitions {

  private final CommandParser commandParser;
  private final Shell shell;
  private final TimelineRepository timelineRepo;
  private UserRepository userRepo;

  public StepDefinitions() {
    this.userRepo = new InMemoryUserRepository();
    commandParser = new CommandParser();
    timelineRepo = new InMemoryTimelineRepository();
    shell = new Shell(commandParser, userRepo, timelineRepo);
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
    final List<Post> posts = timelineRepo.get(user);
    assertTrue(posts.stream().anyMatch(p -> p.getText().equals(post)));
  }

  @Then("^\"(.*?)\" timeline contains (\\d+) posts$")
   public void timeline_contains_posts(String posessive, int expectedCount) throws Throwable {
    final String userName = stripPosessive(posessive);
    final User user = userRepo.get(userName);
    final List<Post> posts = timelineRepo.get(user);
    assertEquals(expectedCount, posts.size());
   }

  String stripPosessive(String posessive) {
    return posessive.replaceAll("'s$", "");
  }

  private String post(String userName, String message) {
    return userName + " -> " + message;
  }
}
