package info.bowkett.abc;

import info.bowkett.abc.commands.Command;
import info.bowkett.abc.commands.PostCommand;

/**
 * Created by jbowkett on 29/08/2014.
 */
public class Shell {
  private final CommandParser parser;
  private UserRepository userRepo;

  public Shell(CommandParser parser, UserRepository userRepo) {
    this.parser = parser;
    this.userRepo = userRepo;
  }


  public void submit(String shellCommand) {
    final Command command = parser.submit(shellCommand);
    final User user = userRepo.get(command.getUserName());
    if (command instanceof PostCommand){
      final String text = ((PostCommand) command).getText();
      user.addPost(text);
    }
  }
}
