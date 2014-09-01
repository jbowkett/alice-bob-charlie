package info.bowkett.abc.dal;

import info.bowkett.abc.domain.User;

/**
 * Created by jbowkett on 29/08/2014.
 */
public interface UserRepository {
  public User get(String userName);
}
