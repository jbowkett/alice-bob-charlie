package info.bowkett.abc.dal;

import info.bowkett.abc.domain.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by jbowkett on 29/08/2014.
 */
public class InMemoryUserRepository implements UserRepository {
  private final ConcurrentMap<String, User> userNameToUserMap = new ConcurrentHashMap<>();

  public User get(String userName) {
    userNameToUserMap.putIfAbsent(userName, new User(userName));

    return userNameToUserMap.get(userName);
  }
}
