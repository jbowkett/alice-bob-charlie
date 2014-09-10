package info.bowkett.abc.dal;

import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;

/**
 * Created by jbowkett on 10/09/2014.
 */
public interface DataRepository {
  User getUser(String userName);

  void addFollowing(User userDoingFollowing, User userToFollow);

  Timeline getTimeline(User user);
}
