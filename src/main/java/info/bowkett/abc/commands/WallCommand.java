package info.bowkett.abc.commands;

import info.bowkett.abc.dal.UserRepository;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.domain.Wall;
import info.bowkett.abc.shell.Console;
import info.bowkett.abc.shell.WallFactory;

/**
 * Wall shell command
 * Created by jbowkett on 31/08/2014.
 */
public class WallCommand implements Command {
  private final String userName;
  private final UserRepository userRepo;
  private final WallFactory wallFactory;

  public WallCommand(String userName, UserRepository userRepo, WallFactory wallFactory) {
    this.userName = userName;
    this.userRepo = userRepo;
    this.wallFactory = wallFactory;
  }

  @Override
  public void execute(Console console) {
    User user = userRepo.get(userName);
    final Wall wall = wallFactory.getWall(user);
    wall.forEachRecentFirst(post -> {
      console
          .print(post.getUser().getName() + " - " + post.getText())
          .print(" ")
          .timestamp(post.getTimestamp())
          .println();
    });
  }

  @Override
  public String getUserName() {
    return userName;
  }
}
