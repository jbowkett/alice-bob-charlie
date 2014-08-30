package info.bowkett.abc;

/**
 * Created by jbowkett on 30/08/2014.
 */
public class Timeformat {
  private static final long ONE_MINUTE = 60 * 1_000;

  public String forDiff(long historicTimeMillis, long nowMillis) {
    final long diff = nowMillis - historicTimeMillis;
    final long diffInMinutes = diff / ONE_MINUTE;
    final String suffix = diffInMinutes == 1 ? " minute ago" : " minutes ago";
    return diffInMinutes + suffix;
  }

  public String forNow(long historicTimeMillis){
    return forDiff(historicTimeMillis, System.currentTimeMillis());
  }
}
