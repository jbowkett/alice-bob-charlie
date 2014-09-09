package info.bowkett.abc.shell;

import info.bowkett.abc.commands.*;
import info.bowkett.abc.dal.FollowRepository;
import info.bowkett.abc.dal.TimelineRepository;
import info.bowkett.abc.dal.UserRepository;

/**
 * Parses commands as specified in the different feature files.
 * Follows the command pattern (Gamma et al.)
 *
 * Created by jbowkett on 27/08/2014.
 */
public class CommandFactory {

  private static final String FOLLOWS = "follows";
  private static final String POST = "->";
  private static final String WALL = "wall";
  private final UserRepository userRepo;
  private final TimelineRepository timelineRepo;
  private final FollowRepository followRepo;
  private final WallFactory wallFactory;

  public CommandFactory(UserRepository userRepo, TimelineRepository timelineRepo, FollowRepository followRepo, WallFactory wallFactory) {
    this.userRepo = userRepo;
    this.timelineRepo = timelineRepo;
    this.followRepo = followRepo;
    this.wallFactory = wallFactory;
  }


  /**
   * @param shellCommand the line given at the shell prompt
   * @return a command instance denoting what should be done along with
   * appropriate information extracted from shellCommand
   */
  public Command getCommand(String shellCommand) {
    final String[] words = shellCommand.split(" ");
    final String userName = words[0].trim();

    if (viewCommand(words)) {
      return new ReadCommand(userName, timelineRepo, userRepo);
    }
    else if (wallCommand(words)) {
      return new WallCommand(userName, userRepo, wallFactory);
    }
    else if (followCommand(words)) {
      return new FollowCommand(userName, words[2], userRepo, followRepo);
    }
    else if (postCommand(words)) {
      final String[] postParts = shellCommand.split("->");
      return new PostCommand(userName, postParts[1].trim(), userRepo, timelineRepo);
    }
    else {
      throw new IllegalArgumentException(shellCommand);
    }
  }

  private boolean viewCommand(String[] words) {
    return words.length == 1;
  }

  private boolean wallCommand(String[] words) {
    return words.length == 2 && words[1].equals(WALL);
  }

  private boolean followCommand(String[] words) {
    return words.length == 3 && words[1].equals(FOLLOWS);
  }

  private boolean postCommand(String[] words) {
    return words.length >= 3 && words[1].equals(POST);
  }
}
