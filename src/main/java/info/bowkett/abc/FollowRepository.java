package info.bowkett.abc;

import java.util.Set;

/**
 * Created by jbowkett on 31/08/2014.
 */
public interface FollowRepository {
  public void addFollowing(User userDoingFollowing, User userToFollow);

  public Subscriptions getSubscriptionsFor(User userDoingFollowing);
}
