package info.bowkett.abc.console;

import org.junit.Test;
import org.junit.Before;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Console Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Aug 30, 2014</pre>
 */
public class TimeformatTest {

  private Timeformat format;

  @Before
  public void before() throws Exception {
    format = new Timeformat();
  }

  private String formatForSeconds(int secondsCount) {
    final Diff diff = new Diff(secondsCount);
    return format.forDiff(diff.startNanos(), diff.endNanos());
  }

  @Test
  public void testTenSecondsAgo() throws Exception {
    final String timeString = formatForSeconds(10);
    assertEquals("10 seconds ago", timeString);
  }

  @Test
  public void testOneMinuteAgo() throws Exception {
    final String timeString = formatForSeconds(60);
    assertEquals("1 minute ago", timeString);
  }

  @Test
  public void test61SecondsAgo() throws Exception {
    final String timeString = formatForSeconds(61);
    assertEquals("1 minute ago", timeString);
  }

  @Test
  public void test119SecondsAgo() throws Exception {
    final String timeString = formatForSeconds(119);
    assertEquals("1 minute ago", timeString);
  }

  @Test
  public void testTwoMinutesAgo() throws Exception {
    final String timeString = formatForSeconds(120);
    assertEquals("2 minutes ago", timeString);
  }

  private class Diff{
    private final int secondsCount;
    private final long  startTime;

    private Diff(int secondsCount){
      this.secondsCount = secondsCount;
      this.startTime = 1_000_000;
    }
    private long startNanos(){
      return TimeUnit.SECONDS.toNanos(startTime);
    }
    private long endNanos(){
      return startNanos() + TimeUnit.SECONDS.toNanos(secondsCount);
    }
  }
}
