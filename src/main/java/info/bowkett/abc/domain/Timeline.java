package info.bowkett.abc.domain;

import info.bowkett.abc.domain.Post;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by jbowkett on 31/08/2014.
 */
public class Timeline {

  private final List<Post> timeline = new LinkedList<>();

  public void forEachRecentFirst(Consumer<Post> action){
    timeline.stream().forEach(action);
  }

  public void add(Post post) {
    timeline.add(post);
    sortInTimestampOrder();
  }

  public int size() {
    return timeline.size();
  }

  public boolean anyMatch(Predicate<? super Post> predicate){
    return timeline.stream().anyMatch(predicate);
  }

  private void sortInTimestampOrder() {
    timeline.sort((p1, p2) -> (int)(p2.getTimestamp() - p1.getTimestamp()));
  }
}
