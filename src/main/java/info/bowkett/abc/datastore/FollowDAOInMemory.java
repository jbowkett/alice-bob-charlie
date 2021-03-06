package info.bowkett.abc.datastore;

import info.bowkett.abc.domain.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Following DAO based on a concurrent map.
 *
 * Created by jbowkett on 31/08/2014.
 */
public class FollowDAOInMemory implements FollowDAO {
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
  public Set<User> getUsersFollowedBy(User userDoingFollowing) {
    final Subscriptions subscriptions = userToSubscriptionsMap.getOrDefault(userDoingFollowing, new Subscriptions());
    return subscriptions.users();
  }

  /**
   * Inner class to model a user and the users they follow
   */
  private class Subscriptions {
    private final Set<User> users = new HashSet<>();

    private void add(User toFollow){
      users.add(toFollow);
    }

    private Set<User> users() {
      return Collections.unmodifiableSet(users);
    }
  }
}
