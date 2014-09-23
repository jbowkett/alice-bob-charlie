package info.bowkett.abc.dao;

import info.bowkett.abc.domain.Post;
import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.domain.Wall;
import org.junit.Test;
import org.junit.Before;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * InMemoryDataRepository Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 15, 2014</pre>
 */
public class DataRepositoryImplTest {

  private UserRepository userRepository;
  private TimelineRepository timelineRepository;
  private FollowRepository followRepository;
  private DataRepositoryImpl dataRepository;
  private User alice;
  private User bob;
  private Timeline aliceTimeline;
  private Timeline bobTimeline;
  private int postCounter;
  private Deque<Post> expectedWall;
  private AbstractSequentialList<Post> actualWall;

  @Before
  public void before() throws Exception {
    userRepository = mock(UserRepository.class);
    timelineRepository = mock(TimelineRepository.class);
    followRepository = mock(FollowRepository.class);
    dataRepository = new DataRepositoryImpl(
        userRepository,
        timelineRepository,
        followRepository
    );
    alice = mock(User.class,"alice");
    bob = mock(User.class, "bob");
    postCounter = 0;
    expectedWall = new LinkedList<>();
    // could use mocks here, but we are starting to disappear into deep mocking
    aliceTimeline = new Timeline();
    bobTimeline = new Timeline();
    when(timelineRepository.get(alice)).thenReturn(aliceTimeline);
    when(timelineRepository.get(bob)).thenReturn(bobTimeline);
  }


  /**
   * Method: getWall(User user)
   */
  @Test
  public void testGetWallIsOrderedCorrectlyWhenPostsAreOneSecondApartInNanoSeconds() throws Exception {
    // posts are 1 second apart in nanoseconds - as this gave rise to the ordering bug

    given_AliceFollowsBob();
    given_APostByAliceAt(1410799220544109000l);
    given_APostByAliceAt(1410799221544109000l);
    given_APostByBobAt(1410799222544109000l);
    given_APostByAliceAt(1410799223544109000l);

    when_viewingWallForAlice();
    then_thePostsAreInterleavedInChronologicalOrder();
  }

  private void then_thePostsAreInterleavedInChronologicalOrder() {
    assertEquals(expectedWall, actualWall);
  }

  private void given_APostByAliceAt(long timestamp) {
    aliceTimeline.add(postBy(alice, timestamp));
  }

  private void given_APostByBobAt(long timestamp) {
    bobTimeline.add(postBy(bob, timestamp));
  }

  private Post postBy(User user, long timestamp) {
    final Post post = new Post(user, "Post#" + (postCounter++), timestamp);
    //ensures expected wall is ordered oldest to newest
    expectedWall.addFirst(post);
    return post;
  }

  private void when_viewingWallForAlice() {
    final Wall wall = dataRepository.getWall(alice);
    actualWall = new LinkedList<>();
    wall.forEachRecentFirst(actualWall::add);
  }

  private void given_AliceFollowsBob() {
    final HashSet<User> whoFollowing = new HashSet<>();
    whoFollowing.add(bob);
    when(followRepository.getUsersFollowedBy(alice)).thenReturn(whoFollowing);
  }
}
