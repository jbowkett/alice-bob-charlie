package info.bowkett.abc;

import java.util.HashSet;
import java.util.Set;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by jbowkett on 01/09/2014.
 */
public class Subscriptions {
  private final Set<User> subscriptions = new HashSet<>();

  public void add(User toFollow){
    subscriptions.add(toFollow);
  }

  public Stream<User> stream() {
    return subscriptions.stream();
  }
}
