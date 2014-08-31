package info.bowkett.abc;

import info.bowkett.abc.commands.PostCommand;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jbowkett on 29/08/2014.
 */
public class User {

  private final String name;

  public User(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final User user = (User) o;
    return name.equals(user.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
