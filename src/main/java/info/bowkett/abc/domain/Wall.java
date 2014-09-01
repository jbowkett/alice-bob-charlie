package info.bowkett.abc.domain;

import info.bowkett.abc.domain.Post;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jbowkett on 01/09/2014.
 */
public class Wall {
  private final List<Post> wall = new LinkedList<>();

  public void forEachRecentFirst(Consumer<Post> consumer){
    this.wall.stream().forEach(consumer);
  }

  public void add(Post post) {
    wall.add(post);
    sortInTimestampOrder();
  }

  private void sortInTimestampOrder() {
    wall.sort((p1, p2) -> (int)(p2.getTimestamp() - p1.getTimestamp()));
  }
}
