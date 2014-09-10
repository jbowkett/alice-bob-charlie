package info.bowkett.abc.dal;

import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;

/**
 * Created by jbowkett on 10/09/2014.
 */
public class InMemoryDataRepository implements DataRepository {

  private final UserRepository userRepository;
  private final TimelineRepository timelineRepository;
  private final FollowRepository followRepository;

  public InMemoryDataRepository(UserRepository userRepository, TimelineRepository timelineRepository, FollowRepository followRepository) {
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
}
