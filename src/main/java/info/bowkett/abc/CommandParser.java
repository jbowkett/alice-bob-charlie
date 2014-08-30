package info.bowkett.abc;

import info.bowkett.abc.commands.Command;
import info.bowkett.abc.commands.PostCommand;
import info.bowkett.abc.commands.ViewCommand;

/**
 * Created by jbowkett on 27/08/2014.
 */
public class CommandParser {

  public Command submit(String shellCommand) {
    final String[] parts = shellCommand.split(" ");

    final String userName = parts[0].trim();

    if(parts.length == 1){
      return new ViewCommand(userName);
    }
    else{
      if (parts.length >= 3 && parts[1].equals("->")){
        final String[] postParts = shellCommand.split("->");
        return new PostCommand(userName, postParts[1].trim());
      }
      else{
        return null;
      }
    }
  }
}
