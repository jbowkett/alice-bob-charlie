package info.bowkett.abc.shell;

/**
 * Created by jbowkett on 30/08/2014.
 */
public class Console {

  private final Timeformat time;

  public Console(Timeformat time) {
    this.time = time;
  }

  public Console print(String post) {
    System.out.print(post);
    return this;
  }

  public Console timestamp(long timestamp) {
    return print('(' + time.forNow(timestamp) + ')');
  }

  public Console println() {
    return print("\n");
  }
}
