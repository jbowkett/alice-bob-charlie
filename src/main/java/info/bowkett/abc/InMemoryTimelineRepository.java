package info.bowkett.abc;

import info.bowkett.abc.domain.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by jbowkett on 30/08/2014.
 */
public class InMemoryTimelineRepository implements TimelineRepository {
  private final ConcurrentMap<User, Timeline> timelines = new ConcurrentHashMap<>();

  @Override
  public Timeline get(User user) {
    timelines.putIfAbsent(user, new Timeline());
    return timelines.get(user);
  }
}
