package info.bowkett.abc.commands;

/**
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

  public String getUserName() {
    return userName;
  }
}
