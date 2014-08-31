package info.bowkett.abc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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
  public Set<User> getSubscriptionsFor(User userDoingFollowing) {
    final Subscriptions subs =
        userToSubscriptionsMap.getOrDefault(userDoingFollowing, new Subscriptions());
    return subs.unmodifiableSet();
  }

  private class Subscriptions {
    private final Set<User> subscriptions = new HashSet<>();

    public void add(User toFollow){
      subscriptions.add(toFollow);
    }

    public Set<User> unmodifiableSet() {
      return Collections.unmodifiableSet(subscriptions);
    }
  }
}
