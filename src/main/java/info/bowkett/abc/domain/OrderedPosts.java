package info.bowkett.abc.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Package private utility model class for DRYing up ordered lists of posts
 * Created by jbowkett on 1/09/2014.
 */
class OrderedPosts {

  private final List<Post> posts = new LinkedList<>();

  void forEachRecentFirst(Consumer<Post> action){
    posts.stream().forEach(action);
  }

  void add(Post post) {
    posts.add(post);
    sortInTimestampOrder();
  }

  int size() {
    return posts.size();
  }

  boolean anyMatch(Predicate<? super Post> predicate){
    return posts.stream().anyMatch(predicate);
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
