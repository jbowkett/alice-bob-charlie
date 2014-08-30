package info.bowkett.abc;

import info.bowkett.abc.commands.Command;
import info.bowkett.abc.commands.PostCommand;

/**
 * Created by jbowkett on 29/08/2014.
 */
public class Shell {
  private final CommandParser parser;
  private UserRepository userRepo;
  private final TimelineRepository timelineRepo;

  public Shell(CommandParser parser, UserRepository userRepo, TimelineRepository timelineRepo) {
    this.parser = parser;
    this.userRepo = userRepo;
    this.timelineRepo = timelineRepo;
  }


  public void submit(String shellCommand) {
    final Command command = parser.submit(shellCommand);
    final User user = userRepo.get(command.getUserName());
    if (command instanceof PostCommand){
      final String text = ((PostCommand) command).getText();
      timelineRepo.get(user).add(new Post(text));
    }
  }
}
