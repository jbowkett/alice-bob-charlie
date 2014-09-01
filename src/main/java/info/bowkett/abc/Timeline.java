package info.bowkett.abc;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by jbowkett on 31/08/2014.
 */
public class Timeline {

  private final List<Post> timeline = new LinkedList<>();

  public Stream<Post> stream() {
    return timeline.stream();
  }

  public void add(Post post) {
    timeline.add(post);
  }

  public int size() {
    return timeline.size();
  }
}
