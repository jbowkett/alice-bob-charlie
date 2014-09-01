package info.bowkett.abc.dal;

import info.bowkett.abc.domain.User;

/**
 * Repository for storing and retrieving users
 * Created by jbowkett on 29/08/2014.
 */
public interface UserRepository {
  /**
   * Gets or creates the user with the specified name
   * @param userName
   * @return the User (never null)
   */
  public User get(String userName);
}
