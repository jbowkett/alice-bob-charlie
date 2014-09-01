package info.bowkett.abc;

/**
 * Created by jbowkett on 30/08/2014.
 */
public class Post {
  private final String text;
  private final long timestamp;
  private final User user;

  public Post(User user, String text){
    this(user, text, System.currentTimeMillis());
  }

  public Post(User user, String text, long timestamp) {
    this.text = text;
    this.timestamp = timestamp;
    this.user = user;
  }

  public String getText() {
    return text;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public User getUser() {
    return user;
  }
}
