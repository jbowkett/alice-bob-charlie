package info.bowkett.abc.commands;

import info.bowkett.abc.dal.DataRepository;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.domain.Wall;
import info.bowkett.abc.console.Console;

/**
 * Wall shell command
 * Created by jbowkett on 31/08/2014.
 */
public class WallCommand implements Command {
  private final String userName;
  private final DataRepository dataRepository;

  public WallCommand(String userName, DataRepository dataRepository) {
    this.userName = userName;
    this.dataRepository = dataRepository;
  }

  @Override
  public void execute(Console console) {
    final User user = dataRepository.getUser(userName);
    final Wall wall = dataRepository.getWall(user);
    wall.forEachRecentFirst(post -> {
      console
          .print(post.getUser().getName() + " - " + post.getText())
          .print(" ")
          .timestamp(post.getTimestamp())
          .println();
    });
  }
}
