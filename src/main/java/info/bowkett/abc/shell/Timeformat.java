package info.bowkett.abc.shell;

import java.util.concurrent.TimeUnit;

/**
 * Created by jbowkett on 30/08/2014.
 */
public class Timeformat {
  private static final long ONE_MINUTE_IN_NANOS = TimeUnit.MINUTES.toNanos(1);

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

  public String forNow(long historicTimeNanos){
    return forDiff(historicTimeNanos, System.nanoTime());
  }
}
