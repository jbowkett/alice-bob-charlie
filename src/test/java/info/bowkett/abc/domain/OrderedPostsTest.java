package info.bowkett.abc.domain;

import org.junit.Test;
import org.junit.Before;

import java.util.AbstractSequentialList;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * OrderedPosts Tester.
 *
 * @author James Bowkett
 * @version 1.0
 * @since <pre>Sep 1, 2014</pre>
 */
public class OrderedPostsTest {

  private OrderedPosts orderedPosts;

  @Before
  public void before() throws Exception {
    this.orderedPosts = new OrderedPosts();
  }

  @Test
  public void testWallWithTwoPostsIsOrderedWithMostRecentFirstWhenAddedInOrder() throws Exception {
    final Post earlierPost = mockPost(10000);
    final Post laterPost = mockPost(12000);
    orderedPosts.add(earlierPost);
    orderedPosts.add(laterPost);
    final AbstractSequentialList<Post> toAssert = new LinkedList<Post>();
    orderedPosts.forEachRecentFirst(toAssert::add);
    assertEquals(laterPost, toAssert.get(0));
    assertEquals(earlierPost, toAssert.get(1));
  }

  @Test
  public void testWallWithTwoPostsIsOrderedWithMostRecentFirstWhenAddedOutOfSequence() throws Exception {
    final Post laterPost = mockPost(12000);
    final Post earlierPost = mockPost(10000);
    orderedPosts.add(laterPost);
    orderedPosts.add(earlierPost);
    final AbstractSequentialList<Post> toAssert = new LinkedList<Post>();
    orderedPosts.forEachRecentFirst(toAssert::add);
    assertEquals(laterPost, toAssert.get(0));
    assertEquals(earlierPost, toAssert.get(1));
  }

  @Test
  public void testWallWithFourPostsIsOrderedWithMostRecentFirstWhenAddedOutOfSequence() throws Exception {
    final Post mostRecent = mockPost(12000);
    final Post secondMostRecent = mockPost(11999);
    final Post secondEarliest = mockPost(11000);
    final Post earliest = mockPost(10000);
    orderedPosts.add(earliest);
    orderedPosts.add(secondEarliest);
    orderedPosts.add(secondMostRecent);
    orderedPosts.add(mostRecent);
    final AbstractSequentialList<Post> toAssert = new LinkedList<>();
    orderedPosts.forEachRecentFirst(toAssert::add);
    assertEquals(mostRecent, toAssert.get(0));
    assertEquals(secondMostRecent, toAssert.get(1));
    assertEquals(secondEarliest, toAssert.get(2));
    assertEquals(earliest, toAssert.get(3));
  }

  private Post mockPost(int timestamp) {
    final User userMock = mock(User.class);
    return new Post(userMock, "test", timestamp);
  }
}
