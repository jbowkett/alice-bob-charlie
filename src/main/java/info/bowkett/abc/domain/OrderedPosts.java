package info.bowkett.abc.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Package private utility model class for DRYing up ordered lists of posts
 * Created by jbowkett on 1/09/2014.
 */
public class OrderedPosts {

  private final List<Post> posts = new LinkedList<>();

  public void forEachRecentFirst(Consumer<Post> action){
    posts.stream().forEach(action);
  }

  public void add(Post post) {
    posts.add(post);
    sortInTimestampOrder();
  }

  private void sortInTimestampOrder() {
    posts.sort((p1, p2) -> {
      final long difference = p2.getTimestamp() - p1.getTimestamp();

      return difference < 0 ? -1:
             difference > 0 ?  1:
                               0;
    });
  }
}
