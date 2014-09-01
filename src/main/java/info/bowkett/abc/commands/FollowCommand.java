package info.bowkett.abc.commands;

/**
 * Follow/subscribe command issued in the shell
 * Created by jbowkett on 31/08/2014.
 */
public class FollowCommand implements Command{

  private final String userNameDoingFollowing, userNameBeingFollowed;

  public FollowCommand(String userNameDoingFollowing, String userNameBeingFollowed) {
    this.userNameDoingFollowing = userNameDoingFollowing;
    this.userNameBeingFollowed = userNameBeingFollowed;
  }

  @Override
  public String getUserName() {
    return userNameDoingFollowing;
  }

  public String getUserNameBeingFollowed() {
    return userNameBeingFollowed;
  }
}
