package info.bowkett.abc.dao;

import info.bowkett.abc.domain.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User repo wrapping a concurrent map (chosen for the more imperative method
 * names)
 * Created by jbowkett on 29/08/2014.
 */
public class InMemoryUserDAO implements UserDAO {
  private final ConcurrentMap<String, User> userNameToUserMap = new ConcurrentHashMap<>();

  /**
   * Gets or creates the user with the specified name
   * @param userName
   * @return the User (never null)
   */
  @Override
  public User get(String userName) {
    userNameToUserMap.putIfAbsent(userName, new User(userName));
    return userNameToUserMap.get(userName);
  }
}
