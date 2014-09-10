package info.bowkett.abc.shell;

import info.bowkett.abc.commands.*;
import info.bowkett.abc.dal.DataRepository;
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
  private final DataRepository dataRepository;
  private final WallFactory wallFactory;

  public CommandFactory(DataRepository dataRepository, WallFactory wallFactory) {
    this.dataRepository = dataRepository;
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
      return new ReadCommand(userName, dataRepository);
    }
    else if (wallCommand(words)) {
      return new WallCommand(userName, dataRepository, wallFactory);
    }
    else if (followCommand(words)) {
      return new FollowCommand(userName, words[2], dataRepository);
    }
    else if (postCommand(words)) {
      final String[] postParts = shellCommand.split("->");
      return new PostCommand(userName, postParts[1].trim(), dataRepository);
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
