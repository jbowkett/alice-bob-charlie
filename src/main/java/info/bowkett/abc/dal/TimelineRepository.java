package info.bowkett.abc.dal;


import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;

/**
 * Created by jbowkett on 30/08/2014.
 */
public interface TimelineRepository {
  Timeline get(User user);
}
