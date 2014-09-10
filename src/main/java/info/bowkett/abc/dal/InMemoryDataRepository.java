package info.bowkett.abc.dal;

import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.domain.Wall;
import info.bowkett.abc.shell.WallFactory;

/**
 * Created by jbowkett on 10/09/2014.
 */
public class InMemoryDataRepository implements DataRepository {

  private final UserRepository userRepository;
  private final TimelineRepository timelineRepository;
  private final FollowRepository followRepository;
  private final WallFactory wallFactory;

  public InMemoryDataRepository(UserRepository userRepository, TimelineRepository timelineRepository, FollowRepository followRepository, WallFactory wallFactory) {
    this.userRepository = userRepository;
    this.timelineRepository = timelineRepository;
    this.followRepository = followRepository;
    this.wallFactory = wallFactory;
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
    return wallFactory.getWall(user);
  }
}
