package info.bowkett.abc;

import info.bowkett.abc.commands.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
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
      final String text = ((PostCommand) command).getText();
      timelineRepo.get(user).add(new Post(user, text));
    }
    else if (command instanceof ViewCommand){
      final Timeline timeline = timelineRepo.get(user);
      timeline.stream().forEach(post -> {
        console.print(post.getText())
            .timestamp(post.getTimestamp())
            .println();
      });
    }
    else if (command instanceof FollowCommand){
      final String toFollow = ((FollowCommand) command).getUserNameBeingFollowed();
      final User userToFollow = userRepo.get(toFollow);
      followRepo.addFollowing(user, userToFollow);
    }
    else if (command instanceof WallCommand){
      final Set<User> subscriptions = followRepo.getSubscriptionsFor(user);
      final Timeline userTimeline = timelineRepo.get(user);
      final Stream<Timeline> timelinesForOthers = subscriptions.stream().map(u -> timelineRepo.get(u));
      final List<Post> wall = new ArrayList<>();
      timelinesForOthers.forEach(timeline -> timeline.stream().forEach(post -> wall.add(post)));
      userTimeline.stream().forEach(post -> wall.add(post));
      wall.sort((o1, o2) -> (int)(o2.getTimestamp() - o1.getTimestamp()));
      wall.stream().forEach(post -> {
        console
            .print(post.getUser().getName() + " - "+post.getText())
            .timestamp(post.getTimestamp())
            .println();
      });
    }
  }
}
