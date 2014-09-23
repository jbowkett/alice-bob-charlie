package info.bowkett.abc.datastore;

import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;
import info.bowkett.abc.domain.Wall;

/**
 * Main data entry point, acts as an aggregate to the other
 * Data Access Object classes.
 *
 * The various find methods should never return null.
 *
 * Created by jbowkett on 10/09/2014.
 */
public interface DataRepository {
  /**
   * @param userName
   * @return a User for the given userName
   */
  User findUser(String userName);

  /**
   * Adds `userDoingFollowing` as a follower of `userToFollow`
   * @param userDoingFollowing
   * @param userToFollow
   */
  void addFollowing(User userDoingFollowing, User userToFollow);

  /**
   * @param user
   * @return the user's timeline
   */
  Timeline findTimeline(User user);

  /**
   * @param user
   * @return the user's wall
   */
  Wall findWall(User user);
}
