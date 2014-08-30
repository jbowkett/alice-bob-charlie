package info.bowkett.abc;

import org.junit.Test;
import org.junit.Before;

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

  @Test
  public void testTenSecondsAgo() throws Exception {
    final String timeString = format.forDiff(1_000_000, 1_010_000);
    assertEquals("10 seconds ago", timeString);
  }

  @Test
  public void testOneMinuteAgo() throws Exception {
    final String timeString = format.forDiff(1_000_000, 1_060_000);
    assertEquals("1 minute ago", timeString);
  }

  @Test
  public void test61SecondsAgo() throws Exception {
    final String timeString = format.forDiff(1_000_000, 1_061_000);
    assertEquals("1 minute ago", timeString);
  }

  @Test
  public void test119SecondsAgo() throws Exception {
    final String timeString = format.forDiff(1_000_000, 1_119_000);
    assertEquals("1 minute ago", timeString);
  }

  @Test
  public void testTwoMinutesAgo() throws Exception {
    final String timeString = format.forDiff(1_000_000, 1_120_000);
    assertEquals("2 minutes ago", timeString);
  }
}
