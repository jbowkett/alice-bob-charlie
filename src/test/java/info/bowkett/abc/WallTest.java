package info.bowkett.abc;

import info.bowkett.abc.domain.Post;
import info.bowkett.abc.domain.User;
import org.junit.Test;
import org.junit.Before;

import java.util.AbstractSequentialList;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * WallFactory Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 1, 2014</pre>
 */
public class WallTest {

  private Wall wall;

  @Before
  public void before() throws Exception {
    this.wall = new Wall();
  }

  @Test
  public void testWallWithTwoPostsIsOrderedWithMostRecentFirstWhenAddedInOrder() throws Exception {
    final Post earlierPost = mockPost(10000);
    final Post laterPost = mockPost(12000);
    wall.add(earlierPost);
    wall.add(laterPost);
    final AbstractSequentialList<Post> toAssert = new LinkedList<Post>();
    wall.forEachRecentFirst(toAssert::add);
    assertEquals(laterPost, toAssert.get(0));
    assertEquals(earlierPost, toAssert.get(1));
  }

  @Test
  public void testWallWithTwoPostsIsOrderedWithMostRecentFirstWhenAddedOutOfSequence() throws Exception {
    final Post laterPost = mockPost(12000);
    final Post earlierPost = mockPost(10000);
    wall.add(laterPost);
    wall.add(earlierPost);
    final AbstractSequentialList<Post> toAssert = new LinkedList<Post>();
    wall.forEachRecentFirst(toAssert::add);
    assertEquals(laterPost, toAssert.get(0));
    assertEquals(earlierPost, toAssert.get(1));
  }

  private Post mockPost(int timestamp) {
    final User userMock = mock(User.class);
    return new Post(userMock, "test", timestamp);
  }
}
