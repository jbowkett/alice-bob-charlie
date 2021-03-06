package info.bowkett.abc.commands;

import info.bowkett.abc.datastore.DataRepository;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.console.Console;

/**
 * Follow command issued in the shell
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
    final User user = dataRepository.findUser(userNameDoingFollowing);
    final User userToFollow = dataRepository.findUser(userNameBeingFollowed);
    dataRepository.addFollowing(user, userToFollow);
  }

  public String getUserName() {
    return userNameDoingFollowing;
  }

  public String getUserNameBeingFollowed() {
    return userNameBeingFollowed;
  }
}
