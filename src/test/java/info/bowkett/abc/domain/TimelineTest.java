package info.bowkett.abc.domain;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.AbstractSequentialList;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Timeline Tester.
 *
 * @author James Bowkett
 * @version 1.0
 * @since <pre>Sep 1, 2014</pre>
 */
public class TimelineTest {

  private Timeline timeline;

  @Before
  public void before() throws Exception {
    timeline = new Timeline();
  }

  @Test
  public void testForEachRecentFirstWhenAddedInPostedDateOrder() throws Exception {
    final User user = mock(User.class);
    final Post later = new Post(user, "test", 123456l);
    final Post earlier = new Post(user, "test", 100l);

    timeline.add(earlier);
    timeline.add(later);
    final AbstractSequentialList<Post> toAssert = new LinkedList<>();
    timeline.forEachRecentFirst(toAssert::add);
    assertEquals(later, toAssert.get(0));
    assertEquals(earlier, toAssert.get(1));
  }

  @Test
  public void testForEachRecentFirstWhenAddedInReversePostedDateOrder() throws Exception {
    final User user = mock(User.class);
    final Post later = new Post(user, "test", 123456l);
    final Post earlier = new Post(user, "test", 100l);

    timeline.add(later);
    timeline.add(earlier);
    final AbstractSequentialList<Post> toAssert = new LinkedList<>();
    timeline.forEachRecentFirst(toAssert::add);
    assertEquals(later, toAssert.get(0));
    assertEquals(earlier, toAssert.get(1));
  }
}
