package info.bowkett.abc.shell;

/**
 * Class representing console output.
 * Could also have handled console input, but did not want this class to
 * require to maintain any state
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

  /**
   * Outputs the difference between now and the given timestamp,
   * as formatted by the timeformat in the constructor
   * @param timestamp
   * @return
   */
  public Console timestamp(long timestamp) {
    return print('(' + time.forNow(timestamp) + ')');
  }

  public Console println() {
    return print("\n");
  }
}
