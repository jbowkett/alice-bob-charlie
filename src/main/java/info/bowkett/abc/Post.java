package info.bowkett.abc;

/**
 * Created by jbowkett on 30/08/2014.
 */
public class Post {
  private final String text;
  private final long timestamp;

  public Post(String text){
    this(text, System.currentTimeMillis());
  }

  public Post(String text, long timestamp) {
    this.text = text;
    this.timestamp = timestamp;
  }

  public String getText() {
    return text;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
