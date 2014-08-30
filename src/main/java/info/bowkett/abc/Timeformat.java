package info.bowkett.abc;

import java.util.concurrent.TimeUnit;

/**
 * Created by jbowkett on 30/08/2014.
 */
public class Timeformat {
  private static final long ONE_MINUTE = 60 * 1_000;

  public String forDiff(long historicTimeMillis, long nowMillis) {
    final long diffInMillis = nowMillis - historicTimeMillis;
    final long rebasedDiff;
    final String unit;
    if(diffInMillis < ONE_MINUTE){
      rebasedDiff = TimeUnit.MILLISECONDS.toSeconds(diffInMillis);
      unit = "second";
    }
    else{
      rebasedDiff = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
      unit = "minute";
    }
    final String plural = rebasedDiff == 1 ? "" : "s";
    return rebasedDiff + " " + unit + plural + " ago";
  }

  public String forNow(long historicTimeMillis){
    return forDiff(historicTimeMillis, System.currentTimeMillis());
  }
}
