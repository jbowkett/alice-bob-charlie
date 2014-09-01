package info.bowkett.abc;

import info.bowkett.abc.domain.User;

/**
 * Created by jbowkett on 29/08/2014.
 */
public interface UserRepository {
  public User get(String userName);
}
