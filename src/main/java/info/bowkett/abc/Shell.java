package info.bowkett.abc;

import info.bowkett.abc.commands.Command;
import info.bowkett.abc.commands.PostCommand;
import info.bowkett.abc.commands.ViewCommand;

import java.util.List;

/**
 * Created by jbowkett on 29/08/2014.
 */
public class Shell {
  private final CommandParser parser;
  private UserRepository userRepo;
  private final TimelineRepository timelineRepo;
  private final Console console;

  public Shell(CommandParser parser, UserRepository userRepo,
               TimelineRepository timelineRepo, Console console) {
    this.parser = parser;
    this.userRepo = userRepo;
    this.timelineRepo = timelineRepo;
    this.console = console;
  }


  public void submit(String shellCommand) {
    final Command command = parser.submit(shellCommand);
    final User user = userRepo.get(command.getUserName());
    if (command instanceof PostCommand){
      final String text = ((PostCommand) command).getText();
      timelineRepo.get(user).add(new Post(text));
    }
    else if (command instanceof ViewCommand){
      final List<Post> timeline = timelineRepo.get(user);
      timeline.stream().forEach(post -> {
        console.print(post.getText())
            .timestamp(post.getTimestamp())
            .println();
      });
    }
  }
}
