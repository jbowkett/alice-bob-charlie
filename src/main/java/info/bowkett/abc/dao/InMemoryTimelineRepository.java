package info.bowkett.abc.dao;

import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Timeline repository wrapping a concurrent map
 *
 * Created by jbowkett on 30/08/2014.
 */
public class InMemoryTimelineRepository implements TimelineRepository {
  private final ConcurrentMap<User, Timeline> timelines = new ConcurrentHashMap<>();

  /**
   * Gets or creates a timeline for the given user
   * @param user
   * @return a timeline (never null)
   */
  @Override
  public Timeline get(User user) {
    timelines.putIfAbsent(user, new Timeline());
    return timelines.get(user);
  }
}
