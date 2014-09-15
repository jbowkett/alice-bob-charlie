package info.bowkett.abc.domain;

import info.bowkett.abc.domain.Post;

import java.util.function.Consumer;

/**
 * A collection of posts assembled together to form a user's wall.
 * Created by jbowkett on 01/09/2014.
 */
public class Wall {
  private final OrderedPosts wall;

  public Wall(OrderedPosts wall) {
    this.wall = wall;
  }

  public void forEachRecentFirst(Consumer<Post> consumer){
    wall.forEachRecentFirst(consumer);
  }
}
