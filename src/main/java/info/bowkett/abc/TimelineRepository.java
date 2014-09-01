package info.bowkett.abc;


import info.bowkett.abc.domain.User;

/**
 * Created by jbowkett on 30/08/2014.
 */
public interface TimelineRepository {
  Timeline get(User user);
}
