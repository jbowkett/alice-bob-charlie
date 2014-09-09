package info.bowkett.abc.commands;

import info.bowkett.abc.dal.TimelineRepository;
import info.bowkett.abc.dal.UserRepository;
import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.shell.Console;

/**
 * Read user's posts shell command
 * Created by jbowkett on 30/08/2014.
 */
public class ReadCommand implements Command {
  private final String userName;
  private final TimelineRepository timelineRepo;
  private final UserRepository userRepo;

  public ReadCommand(String userName, TimelineRepository timelineRepo, UserRepository userRepo) {
    this.userName = userName;
    this.timelineRepo = timelineRepo;
    this.userRepo = userRepo;
  }

  @Override
  public void execute(Console console) {
    final User user = userRepo.get(userName);
    final Timeline timeline = timelineRepo.get(user);
    timeline.forEachRecentFirst(post -> {
      console.print(post.getText())
          .print(" ")
          .timestamp(post.getTimestamp())
          .println();
    });
  }
}
