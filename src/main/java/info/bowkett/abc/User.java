package info.bowkett.abc;

import info.bowkett.abc.commands.PostCommand;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jbowkett on 29/08/2014.
 */
public class User {

  private final List<String> posts = new LinkedList<>();

  private final String name;

  public User(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public List<String> posts() {
    return posts;
  }

  public void addPost(String post) {
    posts.add(post);
  }
}
