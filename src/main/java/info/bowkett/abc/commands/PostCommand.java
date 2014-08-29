package info.bowkett.abc.commands;

import info.bowkett.abc.User;

/**
 * Created by jbowkett on 29/08/2014.
 */
public class PostCommand implements Command {
  private final User user;
  private final String postText;
  public PostCommand(User user, String postText) {
    this.user = user;
    this.postText = postText;
  }

  public String getText() {
    return postText;
  }

  public User getUser() {
    return user;
  }
}
