package info.bowkett.abc.shell;

import info.bowkett.abc.commands.*;

/**
 * Created by jbowkett on 27/08/2014.
 */
public class CommandParser {

  private static final String FOLLOWS = "follows";
  private static final String POST = "->";
  private static final String WALL = "wall";

  public Command submit(String shellCommand) {
    final String[] words = shellCommand.split(" ");
    final String userName = words[0].trim();

    if (viewCommand(words)) {
      return new ViewCommand(userName);
    }
    else if (wallCommand(words)) {
      return new WallCommand(userName);
    }
    else if (followCommand(words)) {
      return new FollowCommand(userName, words[2]);
    }
    else if (postCommand(words)) {
      final String[] postParts = shellCommand.split("->");
      return new PostCommand(userName, postParts[1].trim());
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