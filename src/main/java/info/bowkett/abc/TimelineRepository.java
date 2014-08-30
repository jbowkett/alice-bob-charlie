package info.bowkett.abc;

import java.util.List;

/**
 * Created by jbowkett on 30/08/2014.
 */
public interface TimelineRepository {
  List<Post> get(User user);
}
