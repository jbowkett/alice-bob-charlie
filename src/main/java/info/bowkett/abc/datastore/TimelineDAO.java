package info.bowkett.abc.datastore;


import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;

/**
 * Timeline repository for storage and retrieval of user timelines.
 * Created by jbowkett on 30/08/2014.
 */
public interface TimelineDAO {

  /**
   * Gets or creates a timeline for the given user
   * @param user
   * @return a timeline (never null)
   */
  public Timeline get(User user);
}
