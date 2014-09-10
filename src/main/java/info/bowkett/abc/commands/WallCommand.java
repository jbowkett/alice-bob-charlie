package info.bowkett.abc.commands;

import info.bowkett.abc.dal.DataRepository;
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
  private final DataRepository dataRepository;
  private final WallFactory wallFactory;

  public WallCommand(String userName, DataRepository dataRepository, WallFactory wallFactory) {
    this.userName = userName;
    this.dataRepository = dataRepository;
    this.wallFactory = wallFactory;
  }

  @Override
  public void execute(Console console) {
    final User user = dataRepository.getUser(userName);
    final Wall wall = wallFactory.getWall(user);
    wall.forEachRecentFirst(post -> {
      console
          .print(post.getUser().getName() + " - " + post.getText())
          .print(" ")
          .timestamp(post.getTimestamp())
          .println();
    });
  }
}
