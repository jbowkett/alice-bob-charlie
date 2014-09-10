package info.bowkett.abc.commands;

import info.bowkett.abc.dal.DataRepository;
import info.bowkett.abc.dal.FollowRepository;
import info.bowkett.abc.dal.UserRepository;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.shell.Console;

/**
 * Follow/subscribe command issued in the shell
 * Created by jbowkett on 31/08/2014.
 */
public class FollowCommand implements Command{

  private final String userNameDoingFollowing;
  private final String userNameBeingFollowed;
  private final DataRepository dataRepository;

  public FollowCommand(String userNameDoingFollowing, String userNameBeingFollowed, DataRepository dataRepository) {
    this.userNameDoingFollowing = userNameDoingFollowing;
    this.userNameBeingFollowed = userNameBeingFollowed;
    this.dataRepository = dataRepository;
  }

  @Override
  public void execute(Console console) {
    final User user = dataRepository.getUser(userNameDoingFollowing);
    final User userToFollow = dataRepository.getUser(userNameBeingFollowed);
    dataRepository.addFollowing(user, userToFollow);
  }

  public String getUserName() {
    return userNameDoingFollowing;
  }

  public String getUserNameBeingFollowed() {
    return userNameBeingFollowed;
  }
}
