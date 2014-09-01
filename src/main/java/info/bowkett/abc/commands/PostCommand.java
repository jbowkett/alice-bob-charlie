package info.bowkett.abc.commands;

/**
 * Post message shell command
 * Created by jbowkett on 29/08/2014.
 */
public class PostCommand implements Command {
  private final String userName;
  private final String postText;
  public PostCommand(String user, String postText) {
    this.userName = user;
    this.postText = postText;
  }

  public String getText() {
    return postText;
  }

  @Override
  public String getUserName() {
    return userName;
  }
}
