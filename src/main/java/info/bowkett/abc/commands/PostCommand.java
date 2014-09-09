package info.bowkett.abc.commands;

import info.bowkett.abc.dal.TimelineRepository;
import info.bowkett.abc.dal.UserRepository;
import info.bowkett.abc.domain.Post;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.shell.Console;

/**
 * Post message shell command
 * Created by jbowkett on 29/08/2014.
 */
public class PostCommand implements Command {
  private final String userName;
  private final String postText;
  private final UserRepository userRepo;
  private TimelineRepository timelineRepo;

  public PostCommand(String user, String postText, UserRepository userRepo, TimelineRepository timelineRepo) {
    this.userName = user;
    this.postText = postText;
    this.userRepo = userRepo;
    this.timelineRepo = timelineRepo;
  }

  @Override
  public void execute(Console console){
    final User user = userRepo.get(userName);
    timelineRepo.get(user).add(new Post(user, postText));
  }

  public String getText() {
    return postText;
  }

  @Override
  public String getUserName() {
    return userName;
  }
}
