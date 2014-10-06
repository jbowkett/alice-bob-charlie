package info.bowkett.abc.datastore;

import info.bowkett.abc.domain.User;

import java.util.Set;

/**
 * DAO for storing and retrieving who a user has followed.
 * Created by jbowkett on 31/08/2014.
 */
public interface FollowDAO {
  /**
   * registers `userDoingFollowing` to now follow `userToFollow`
   * @param userDoingFollowing
   * @param userToFollow
   */
  public void addFollowing(User userDoingFollowing, User userToFollow);

  /**
   * Get the users that userDoingFollowing has followed
   * @param userDoingFollowing
   * @return a Subscription for the user, which may be empty, but is never null
   */
  public Set<User> getUsersFollowedBy(User userDoingFollowing);
}
