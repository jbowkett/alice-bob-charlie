package info.bowkett.abc.dal;

import info.bowkett.abc.domain.*;

import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by jbowkett on 10/09/2014.
 */
public class DataRepositoryImpl implements DataRepository {

  private final UserRepository userRepository;
  private final TimelineRepository timelineRepository;
  private final FollowRepository followRepository;

  public DataRepositoryImpl(UserRepository userRepository,
                            TimelineRepository timelineRepository,
                            FollowRepository followRepository) {
    this.userRepository = userRepository;
    this.timelineRepository = timelineRepository;
    this.followRepository = followRepository;
  }

  @Override
  public User getUser(String userName) {
    return userRepository.get(userName);
  }

  @Override
  public void addFollowing(User userDoingFollowing, User userToFollow) {
    followRepository.addFollowing(userDoingFollowing, userToFollow);
  }

  @Override
  public Timeline getTimeline(User user) {
    return timelineRepository.get(user);
  }

  @Override
  public Wall getWall(User user) {
    final Set<User> usersBeingFollowed = followRepository.getUsersFollowedBy(user);
    final Stream<Timeline> timelinesForOthers = usersBeingFollowed.stream().map(timelineRepository::get);
    final Timeline userTimeline = timelineRepository.get(user);
    final OrderedPosts wallPosts = new OrderedPosts();
    timelinesForOthers.forEach(timeline -> timeline.forEachRecentFirst(wallPosts::add));
    userTimeline.forEachRecentFirst(wallPosts::add);
    return new Wall(wallPosts);
  }
}
