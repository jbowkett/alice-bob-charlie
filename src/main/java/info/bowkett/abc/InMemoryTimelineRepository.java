package info.bowkett.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by jbowkett on 30/08/2014.
 */
public class InMemoryTimelineRepository implements TimelineRepository {
  private final ConcurrentMap<User, List<Post>> timelines = new ConcurrentHashMap<>();

  @Override
  public List<Post> get(User user) {
    timelines.putIfAbsent(user, new ArrayList<>());
    return timelines.get(user);
  }
}
