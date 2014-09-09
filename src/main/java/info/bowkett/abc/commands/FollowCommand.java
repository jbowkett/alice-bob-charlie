package info.bowkett.abc.commands;

import info.bowkett.abc.dal.FollowRepository;
import info.bowkett.abc.dal.UserRepository;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.shell.Console;

/**
 * Follow/subscribe command issued in the shell
 * Created by jbowkett on 31/08/2014.
 */
public class FollowCommand implements Command{

  private final String userNameDoingFollowing, userNameBeingFollowed;
  private UserRepository userRepo;
  private FollowRepository followRepo;

  public FollowCommand(String userNameDoingFollowing, String userNameBeingFollowed, UserRepository userRepo, FollowRepository followRepo) {
    this.userNameDoingFollowing = userNameDoingFollowing;
    this.userNameBeingFollowed = userNameBeingFollowed;
    this.userRepo = userRepo;
    this.followRepo = followRepo;
  }

  @Override
  public void execute(Console console) {
    final User user = userRepo.get(userNameDoingFollowing);
    final User userToFollow = userRepo.get(userNameBeingFollowed);
    followRepo.addFollowing(user, userToFollow);
  }

  @Override
  public String getUserName() {
    return userNameDoingFollowing;
  }

  public String getUserNameBeingFollowed() {
    return userNameBeingFollowed;
  }
}
