package info.bowkett.abc;

import info.bowkett.abc.commands.Command;
import info.bowkett.abc.commands.PostCommand;

/**
 * Created by jbowkett on 27/08/2014.
 */
public class CommandParser {
  private final UserRepository userRepo;

  public CommandParser(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  public Command submit(String shellCommand) {
    final String[] parts = shellCommand.split("->");

    final String userName = parts[0].trim();
    final User user = userRepo.get(userName);
    final PostCommand post = new PostCommand(user, parts[1].trim());
    user.addPost(post);
    return post;
  }
}
