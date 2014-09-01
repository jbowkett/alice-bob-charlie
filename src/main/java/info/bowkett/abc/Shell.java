package info.bowkett.abc;

import info.bowkett.abc.commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by jbowkett on 29/08/2014.
 */
public class Shell {
  private final CommandParser parser;
  private UserRepository userRepo;
  private final TimelineRepository timelineRepo;
  private final Console console;
  private final FollowRepository followRepo;

  public Shell(CommandParser parser, UserRepository userRepo,
               TimelineRepository timelineRepo, Console console,
               FollowRepository followRepo) {
    this.parser = parser;
    this.userRepo = userRepo;
    this.timelineRepo = timelineRepo;
    this.console = console;
    this.followRepo = followRepo;
  }


  public void submit(String shellCommand) {
    final Command command = parser.submit(shellCommand);
    final User user = userRepo.get(command.getUserName());
    if (command instanceof PostCommand){
      doPost((PostCommand) command, user);
    }
    else if (command instanceof ViewCommand){
      doView(user);
    }
    else if (command instanceof FollowCommand){
      doFollow((FollowCommand) command, user);
    }
    else if (command instanceof WallCommand){
      doWall(user);
    }
  }

  private void doPost(PostCommand command, User user) {
    final String text = command.getText();
    timelineRepo.get(user).add(new Post(user, text));
  }

  private void doView(User user) {
    final Timeline timeline = timelineRepo.get(user);
    timeline.forEachRecentFirst(post -> {
      console.print(post.getText())
          .print(" ")
          .timestamp(post.getTimestamp())
          .println();
    });
  }

  private void doFollow(FollowCommand command, User user) {
    final String toFollow = command.getUserNameBeingFollowed();
    final User userToFollow = userRepo.get(toFollow);
    followRepo.addFollowing(user, userToFollow);
  }

  private void doWall(User user) {
    final Subscriptions subscriptions = followRepo.getSubscriptionsFor(user);
    final Stream<Timeline> timelinesForOthers = subscriptions.stream().map(timelineRepo::get);
    final Timeline userTimeline = timelineRepo.get(user);
    final List<Post> wall = new ArrayList<>();
    timelinesForOthers.forEach(timeline -> timeline.forEachRecentFirst(wall::add));
    userTimeline.forEachRecentFirst(wall::add);
    wall.sort((o1, o2) -> (int)(o2.getTimestamp() - o1.getTimestamp()));
    wall.stream().forEach(post -> {
      console
          .print(post.getUser().getName() + " - "+post.getText())
          .print(" ")
          .timestamp(post.getTimestamp())
          .println();
    });
  }
}
