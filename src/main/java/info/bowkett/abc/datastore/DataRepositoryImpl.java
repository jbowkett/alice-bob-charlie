package info.bowkett.abc.datastore;

import info.bowkett.abc.domain.*;

import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by jbowkett on 10/09/2014.
 */
public class DataRepositoryImpl implements DataRepository {

  private final UserDAO userDAO;
  private final TimelineDAO timelineDAO;
  private final FollowDAO followDAO;

  public DataRepositoryImpl(UserDAO userDAO,
                            TimelineDAO timelineDAO,
                            FollowDAO followDAO) {
    this.userDAO = userDAO;
    this.timelineDAO = timelineDAO;
    this.followDAO = followDAO;
  }

  @Override
  public User findUser(String userName) {
    return userDAO.read(userName);
  }

  @Override
  public void addFollowing(User userDoingFollowing, User userToFollow) {
    followDAO.addFollowing(userDoingFollowing, userToFollow);
  }

  @Override
  public Timeline findTimeline(User user) {
    return timelineDAO.get(user);
  }

  @Override
  public Wall findWall(User user) {
    final Set<User> usersBeingFollowed = followDAO.getUsersFollowedBy(user);
    final Stream<Timeline> timelinesForOthers = usersBeingFollowed.stream().map(timelineDAO::get);
    final Timeline userTimeline = timelineDAO.get(user);
    final OrderedPosts wallPosts = new OrderedPosts();
    timelinesForOthers.forEach(timeline -> timeline.forEachRecentFirst(wallPosts::add));
    userTimeline.forEachRecentFirst(wallPosts::add);
    return new Wall(wallPosts);
  }
}
