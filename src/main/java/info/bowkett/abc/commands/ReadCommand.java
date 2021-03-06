package info.bowkett.abc.commands;

import info.bowkett.abc.datastore.DataRepository;
import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.console.Console;

/**
 * Read user's posts shell command
 * Created by jbowkett on 30/08/2014.
 */
public class ReadCommand implements Command {
  private final String userName;
  private final DataRepository dataRepository;

  public ReadCommand(String userName, DataRepository dataRepository) {
    this.userName = userName;
    this.dataRepository = dataRepository;
  }

  @Override
  public void execute(Console console) {
    final User user = dataRepository.findUser(userName);
    final Timeline timeline = dataRepository.findTimeline(user);
    timeline.forEachRecentFirst(post -> {
      console
          .print(post.getText())
          .print(" ")
          .timestamp(post.getTimestamp())
          .println();
    });
  }
}
