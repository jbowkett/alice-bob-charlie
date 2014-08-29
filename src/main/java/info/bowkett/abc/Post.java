package info.bowkett.abc;

/**
 * Created by jbowkett on 29/08/2014.
 */
public class Post implements Command {
  private final User user;
  private final String postText;
  public Post(User user, String postText) {
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
