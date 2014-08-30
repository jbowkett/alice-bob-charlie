package info.bowkett.abc;

import info.bowkett.abc.commands.Command;
import info.bowkett.abc.commands.PostCommand;

/**
 * Created by jbowkett on 27/08/2014.
 */
public class CommandParser {

  public Command submit(String shellCommand) {
    final String[] parts = shellCommand.split("->");

    final String userName = parts[0].trim();
    return new PostCommand(userName, parts[1].trim());
  }
}
