package info.bowkett.abc.datastore;


import info.bowkett.abc.domain.Timeline;
import info.bowkett.abc.domain.User;

/**
 * Timeline DAO for retrieval of user timelines.
 * Created by jbowkett on 30/08/2014.
 */
public interface TimelineDAO {

  /**
   * Gets or creates a timeline for the given user
   * @param user
   * @return a timeline (never null)
   */
  public Timeline read(User user);
}
