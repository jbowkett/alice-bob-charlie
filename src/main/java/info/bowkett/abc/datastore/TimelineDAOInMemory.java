package info.bowkett.abc.datastore;

import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Timeline DAO wrapping a concurrent map
 *
 * Created by jbowkett on 30/08/2014.
 */
public class TimelineDAOInMemory implements TimelineDAO {
  private final ConcurrentMap<User, Timeline> timelines = new ConcurrentHashMap<>();

  /**
   * Gets or creates a timeline for the given user
   * @param user
   * @return a timeline (never null)
   */
  @Override
  public Timeline read(User user) {
    timelines.putIfAbsent(user, new Timeline());
    return timelines.get(user);
  }
}
