package info.bowkett.abc.shell;

import java.util.concurrent.TimeUnit;

/**
 * Formats time differences into sentences such as:
 * X seconds ago
 * Y minutes ago
 * Created by jbowkett on 30/08/2014.
 */
public class Timeformat {
  private static final long ONE_MINUTE_IN_NANOS = TimeUnit.MINUTES.toNanos(1);

  /**
   * @param historicTimeNanos the earlier time in nanoseconds
   * @param nowMillisNanos the later time in nanoseconds
   * @return time difference as a string
   */
  public String forDiff(long historicTimeNanos, long nowMillisNanos) {
    final long diffInNanos = nowMillisNanos - historicTimeNanos;
    final long rebasedDiff;
    final String unit;
    if(diffInNanos < ONE_MINUTE_IN_NANOS){
      rebasedDiff = TimeUnit.NANOSECONDS.toSeconds(diffInNanos);
      unit = "second";
    }
    else{
      rebasedDiff = TimeUnit.NANOSECONDS.toMinutes(diffInNanos);
      unit = "minute";
    }
    final String plural = rebasedDiff == 1 ? "" : "s";
    return rebasedDiff + " " + unit + plural + " ago";
  }

  /**
   * @param historicTimeNanos
   * @return forDiff for now given by the system time
   */
  public String forNow(long historicTimeNanos){
    return forDiff(historicTimeNanos, System.nanoTime());
  }
}
