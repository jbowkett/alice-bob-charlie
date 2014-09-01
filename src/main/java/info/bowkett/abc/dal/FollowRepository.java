package info.bowkett.abc.dal;

import info.bowkett.abc.Subscriptions;
import info.bowkett.abc.domain.User;

/**
 * Created by jbowkett on 31/08/2014.
 */
public interface FollowRepository {
  public void addFollowing(User userDoingFollowing, User userToFollow);

  public Subscriptions getSubscriptionsFor(User userDoingFollowing);
}
