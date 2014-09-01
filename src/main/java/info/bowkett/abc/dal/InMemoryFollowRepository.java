package info.bowkett.abc.dal;

import info.bowkett.abc.FollowRepository;
import info.bowkett.abc.Subscriptions;
import info.bowkett.abc.domain.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by jbowkett on 31/08/2014.
 */
public class InMemoryFollowRepository implements FollowRepository {
  private final ConcurrentMap<User, Subscriptions> userToSubscriptionsMap = new ConcurrentHashMap<>();

  @Override
  public void addFollowing(User userDoingFollowing, User userToFollow) {
    userToSubscriptionsMap.putIfAbsent(userDoingFollowing, new Subscriptions());
    userToSubscriptionsMap.computeIfPresent(userDoingFollowing, (k, f) -> {
      f.add(userToFollow);
      return f;
    });
  }

  @Override
  public Subscriptions getSubscriptionsFor(User userDoingFollowing) {
    return userToSubscriptionsMap.getOrDefault(userDoingFollowing, new Subscriptions());
  }
}
