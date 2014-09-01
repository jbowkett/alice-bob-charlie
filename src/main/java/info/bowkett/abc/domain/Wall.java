package info.bowkett.abc.domain;

import info.bowkett.abc.domain.Post;

import java.util.function.Consumer;

/**
 * A collection of posts assembled together to form a user's wall.
 * Created by jbowkett on 01/09/2014.
 */
public class Wall {
  private final OrderedPosts wall = new OrderedPosts();

  public void forEachRecentFirst(Consumer<Post> consumer){
    wall.forEachRecentFirst(consumer);
  }

  public void add(Post post) {
    wall.add(post);
  }
}
