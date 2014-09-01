package info.bowkett.abc.domain;

import info.bowkett.abc.domain.Post;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Domain model object for an ordered set of a user's posts i.e. a timeline.
 * Created by jbowkett on 31/08/2014.
 */
public class Timeline {

  private final OrderedPosts timeline = new OrderedPosts();

  public void forEachRecentFirst(Consumer<Post> action){
    timeline.forEachRecentFirst(action);
  }

  public void add(Post post) {
    timeline.add(post);
  }

  public int size() {
    return timeline.size();
  }

  public boolean anyMatch(Predicate<? super Post> predicate){
    return timeline.anyMatch(predicate);
  }
}
